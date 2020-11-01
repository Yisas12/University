package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.SetContClassEvent;

public class SetContClassEventBuilder extends Builder<Event>{

	SetContClassEventBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public SetContClassEventBuilder() {
		super("set_cont_class");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		int t = data.getInt("time");
		JSONArray array = new JSONArray();
		array = data.getJSONArray("info");
		
		List<Pair<String, Integer>> cont = new ArrayList<Pair<String, Integer>>();
		
		for(int i = 0; i < array.length(); i++) {
			
			JSONObject obj = new JSONObject();
			obj = array.getJSONObject(i);
			
			String id = obj.getString("vehicle");
			int contClass = obj.getInt("class");
			Pair<String, Integer> par = new Pair<String, Integer>(id, contClass);

			cont.add(par);
		}
		
		SetContClassEvent sce = new SetContClassEvent(t, cont);
		return sce;
	}

}
