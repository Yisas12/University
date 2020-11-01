package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

public class Vehicle extends SimulatedObject{
	private List<Junction> itinerario;
	private int maxSpeed;
	private int currentSpeed;
	private VehicleStatus state;
	private Road road;
	private int location;
	private int polutionGrade;
	private int totalPolution;
	private int totalDistance;
	private int index;
	JSONObject jo;
	private List<Junction> itinerario_lectura; 
	private Iterator<Junction> iter;
	
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) {
		super(id);
		
		if(maxSpeed < 0 || (contClass < 0 || contClass > 10) || itinerary.size() < 2) {
			throw new IllegalArgumentException();
		}
		
		this.itinerario = itinerary;
		this.itinerario_lectura = Collections.unmodifiableList(new ArrayList<>(itinerary));
		this.maxSpeed = maxSpeed;
		this.polutionGrade = contClass;
		this.currentSpeed = 0;
		this.road = null;
		this.index = 0;
		this.location = 0;
		this.totalPolution = 0;
		this.totalDistance = 0;
		this.state = VehicleStatus.PENDING;
		this.iter = itinerario.iterator();
	}

	@Override
	void advance(int time) {
		 int locPrevia = this.location;
		 
		if(this.state.equals(VehicleStatus.TRAVELING)) {
			//a
			this.location = Math.min(this.location + this.currentSpeed, this.road.getLenght());
			
			//b
			this.totalDistance += this.location - locPrevia;
			int c = this.polutionGrade * (this.location - locPrevia);
			this.totalPolution += c;
			this.road.addContamination(c);
			
			//c
			if(this.location >= this.road.getLenght()) {
				
				this.road.getDestJunc().enter(this);
				this.currentSpeed = 0;
				this.state = VehicleStatus.WAITING;
				this.index++;
			}	 
		}	
	}

	@Override
	public JSONObject report() {
		jo = new JSONObject();
		
		jo.put("distance", this.totalDistance);
		jo.put("co2", this.totalPolution);
		jo.put("id" ,this._id);
		jo.put("class", this.polutionGrade);
		jo.put("speed", this.currentSpeed);
		jo.put("status", this.state);
		
		if(this.road != null && this.state != VehicleStatus.PENDING && this.state != VehicleStatus.ARRIVED) {
			jo.put("road", this.road._id);
			jo.put("location", this.location);
		}
		
		return jo;
	}
	
	void setSpeed(int s) {
		if(s < 0) {
			throw new IllegalArgumentException();
		}
		
		this.currentSpeed = Math.min(s, this.maxSpeed);
	}
	
	void setContaminationClass(int c) {
		if(c < 0 || c > 10) {
			throw new IllegalArgumentException();
		}
		
		this.polutionGrade = c;
	}
	
	void moveToNextRoad() {
		if(this.state == VehicleStatus.PENDING || this.state == VehicleStatus.WAITING) {
			if(this.index == 0 && this.state == VehicleStatus.PENDING) {
				Road r = iter.next().roadTo(iter.next());
				r.enter(this);
				this.road = r; 
				this.state = VehicleStatus.TRAVELING;
				this.index++;
			}
			else {
				
				this.road.exit(this);
				this.location = 0; 
				this.currentSpeed = 0;

				if(this.index >= itinerario.size()) {//fin de trayecto
					this.state = VehicleStatus.ARRIVED;
					this.road = null;
				}
				else {
					Road r = this.road.getDestJunc().roadTo(iter.next());
					r.enter(this);
					this.road = r; 
					this.state = VehicleStatus.TRAVELING;
					
				}
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	public Road getRoad() {
		return this.road;
	}

	public int getV() {
		// TODO Auto-generated method stub
		return this.currentSpeed;
	}

	public int getLocation() {
		return this.location;
	}

	public int getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(int currentSpeed) {
		this.currentSpeed = currentSpeed;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public int getPolutionGrade() {
		return polutionGrade;
	}

	public void setPolutionGrade(int polutionGrade) {
		this.polutionGrade = polutionGrade;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public VehicleStatus getState() {
		return state;
	}

	public void setState(VehicleStatus state) {
		this.state = state;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public String getId() {
		return this._id;
	}

	public int getTotalPolution() {
		return totalPolution;
	}

	public void setTotalPolution(int totalPolution) {
		this.totalPolution = totalPolution;
	}

	public List<Junction> getItinerario() {
		return itinerario;
	}

	public void setItinerario(List<Junction> itinerario) {
		this.itinerario = itinerario;
	}

	public int getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}

	public void setRoad(Road road) {
		this.road = road;
	}
	
	
}
