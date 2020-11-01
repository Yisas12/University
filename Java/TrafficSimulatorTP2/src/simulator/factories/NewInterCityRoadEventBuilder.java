package simulator.factories;


import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewIntercityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder{

	NewInterCityRoadEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
	}

	protected Event createTheInstance(JSONObject data) {

		int t = data.getInt("time");
		String id = data.getString("id");
		String src = data.getString("src");
		String dest = data.getString("dest");
		int length = data.getInt("length");
		int contClass = data.getInt("co2limit");
		int maxspeed = data.getInt("maxspeed");
		Weather weather = Weather.valueOf(data.getString("weather"));

		return new NewIntercityRoadEvent(t, id, src, dest, length, contClass, maxspeed, weather);
	}
}
