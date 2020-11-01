package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event>{

	NewVehicleEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public NewVehicleEventBuilder() {
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		int t = data.getInt("time");
		String id = data.getString("id");
		int maxspeed = data.getInt("maxspeed");
		int contClass = data.getInt("class");
		
		JSONArray aux = new JSONArray();
		aux = data.getJSONArray("itinerary");
		List<String> itinerary = new ArrayList<String>();
		
		for(int i = 0; i < aux.length(); i++) {
			
			itinerary.add(aux.getString(i));
		}
		
		return new NewVehicleEvent(t, id, maxspeed, contClass, itinerary);
	}

}
