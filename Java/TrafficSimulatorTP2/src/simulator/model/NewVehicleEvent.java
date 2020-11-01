package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class NewVehicleEvent extends Event{

	private String id;
	private int maxSpeed;
	private int contClass;
	private List<String> itinerary_str;
	private List<Junction> itinerary;
	
	public NewVehicleEvent(int time, String id, int maxSpeed,
			int contClass, List<String> itinerary) {
		super(time);
		this.id = id;
		this.contClass = contClass;
		this.maxSpeed = maxSpeed;
		this.itinerary_str = itinerary;
		this.itinerary = new ArrayList<Junction>();
	}

	@Override
	void execute(RoadMap map) {
		//hay que convertir la lista de strings que nos llega, en una lista de junction
		 for(String id : this.itinerary_str){
			
			itinerary.add(map.getJunction(id));
		}
		
		Vehicle v = new Vehicle(this.id, this.maxSpeed, this.contClass, this.itinerary);
		map.addVehicle(v);
		v.moveToNextRoad();
	}

	@Override
	public String toString() {
	return "New Vehicle '" + id + "'" ;
	}
}
