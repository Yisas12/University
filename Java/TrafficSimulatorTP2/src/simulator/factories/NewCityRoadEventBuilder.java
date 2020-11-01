package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewCityRoadEvent;
import simulator.model.Weather;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder{

	NewCityRoadEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public NewCityRoadEventBuilder() {
		super("new_city_road");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		int t = data.getInt("time");
		String id = data.getString("id");
		String src = data.getString("src");
		String dest = data.getString("dest");
		int lenght = data.getInt("length");
		int contLimit = data.getInt("co2limit");
		int maxspeed = data.getInt("maxspeed");
		Weather weather = Weather.valueOf(data.getString("weather"));
		
		NewCityRoadEvent new_road = new NewCityRoadEvent(t, id, src, dest, lenght, contLimit, maxspeed, weather);
		
		return new_road;
	}

}
