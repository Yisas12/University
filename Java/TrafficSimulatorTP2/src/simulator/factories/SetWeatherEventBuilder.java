package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetWeatherEvent;
import simulator.model.Weather;

public class SetWeatherEventBuilder extends Builder<Event>{

	SetWeatherEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public SetWeatherEventBuilder() {
		super("set_weather");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		int t = data.getInt("time");
		JSONArray array = new JSONArray();
		array = data.getJSONArray("info");
		
		List<Pair<String,Weather>> ws = new ArrayList <Pair<String,Weather>>();
		
		for(int i = 0; i < array.length(); i++) {
			
			JSONObject obj = new JSONObject();
			obj = array.getJSONObject(i);
			
			String id = obj.getString("road");
			Weather w = Weather.valueOf(obj.getString("weather"));
			Pair<String,Weather> par = new Pair<String,Weather>(id, w);

			ws.add(par);
		}
		
		return new SetWeatherEvent(t, ws);
	}

}
