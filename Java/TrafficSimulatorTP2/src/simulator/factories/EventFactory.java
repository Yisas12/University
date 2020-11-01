package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public class EventFactory extends Builder<Event>{

	EventFactory(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
