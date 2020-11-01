package simulator.model;

import java.util.List;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public abstract class Road extends SimulatedObject{

	protected Junction srcJunc;
	protected Junction destJunc;
	protected int lenght;
	protected int maxSpeed;
	protected int actLimitSpeed;
	protected int pollutionAlarm;
	protected Weather ambCond;
	protected int totalPollution;
	protected List<Vehicle> vehicles;
	protected Comparator<Vehicle> comp;
	JSONObject jo;
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed,
			int contLimit, int lenght, Weather weather) { 
		super(id);
		
		if(maxSpeed < 0 || contLimit < 0 || lenght < 0
				|| srcJunc == null || destJunc == null || weather == null)
			throw new IllegalArgumentException();

		
		this.srcJunc = srcJunc;
		this.destJunc = destJunc;
		this.maxSpeed = maxSpeed;
		this.actLimitSpeed = this.maxSpeed;
		this.pollutionAlarm = contLimit;
		this.lenght = lenght;
		this.ambCond = weather;
			
		 this.comp = new Comparator<Vehicle>() {
			 
		public int compare(Vehicle v1, Vehicle v2) {
			if(v1.getLocation() > v2.getLocation())
				return -1;
			else if(v1.getLocation() < v2.getLocation())
				return 1;
			else return 0;

		}};
		this.vehicles = new SortedArrayList<Vehicle>(this.comp);
		// TODO Auto-generated constructor stub
	}

	@Override
	void advance(int time) { 
		// TODO Auto-generated method stub
		//1
		reduceTotalContamination();
		//2
		updateSpeedLimit();
		//3
		
		for(Vehicle v: this.vehicles) {
			int y = this.calculateVehicleSpeed(v);
			v.setSpeed(y);
			v.advance(time);
		}
		
		this.vehicles.sort(this.comp);
	}

	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		jo = new JSONObject();
		jo.put("speedlimit", this.actLimitSpeed);
		jo.put("co2", this.totalPollution);
		jo.put("weather", this.ambCond.toString());
		
		JSONArray array_vehiculos = new JSONArray();
		
		for(Vehicle v : this.vehicles) {
			array_vehiculos.put(v.getId());
		}
	
		jo.put("vehicles", array_vehiculos);
		jo.put("id", this._id);
		
		return jo;
	}
	
	void enter(Vehicle v) {
		if(v.getCurrentSpeed() != 0 || v.getLocation() != 0)
			throw new IllegalArgumentException();
		this.vehicles.add(v);
	}
	
	void exit(Vehicle v) {
		this.vehicles.remove(v);
	}
	
	void setWeather(Weather w) {
		if(w == null)
			throw new IllegalArgumentException();
		this.ambCond = w;
	}
	
	void addContamination(int c) {
		if(c < 0)
			throw new IllegalArgumentException();
		this.totalPollution += c;
	}
	
	public Weather getAmbCond() {
		return ambCond;
	}

	public void setAmbCond(Weather ambCond) {
		this.ambCond = ambCond;
	}
	

	public Road getRoad() {
		return this;
	}
	
	public Junction getSrcJunc() {
		return srcJunc;
	}

	public void setSrcJunc(Junction srcJunc) {
		this.srcJunc = srcJunc;
	}

	public Junction getDestJunc() {
		return destJunc;
	}

	public void setDestJunc(Junction destJunc) {
		this.destJunc = destJunc;
	}
	

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public int getActLimitSpeed() {
		return actLimitSpeed;
	}

	public void setActLimitSpeed(int actLimitSpeed) {
		this.actLimitSpeed = actLimitSpeed;
	}
	
	public String getId() {
		return this._id;
	}
	
	

	public int getTotalPollution() {
		return totalPollution;
	}

	public void setTotalPollution(int totalPollution) {
		this.totalPollution = totalPollution;
	}
	
	

	public int getPollutionAlarm() {
		return pollutionAlarm;
	}

	public void setPollutionAlarm(int pollutionAlarm) {
		this.pollutionAlarm = pollutionAlarm;
	}
	
	

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	abstract void reduceTotalContamination();
	abstract void updateSpeedLimit();
	abstract int calculateVehicleSpeed(Vehicle v);

}
