package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.DequeingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchingStrategy;
import simulator.model.NewJunctionEvent;

public class NewJunctionEventBuilder extends Builder<Event>{

	private Factory<LightSwitchingStrategy> lssFactory;
	private Factory<DequeingStrategy> dqsFactory;
	
	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy>
	lssFactory, Factory<DequeingStrategy> dqsFactory) {
		super("new_junction");
		this.lssFactory = lssFactory;
		this.dqsFactory = dqsFactory;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected Event createTheInstance(JSONObject data) {
		// TODO Auto-generated method stub
		int t = data.getInt("time");
		String id = data.getString("id");
		
		JSONArray coor = new JSONArray();
		coor = data.getJSONArray("coor");
		int x = coor.getInt(0);
		int y = coor.getInt(1);
		
		JSONObject aux = new JSONObject();
		aux = data.getJSONObject("ls_strategy");
		JSONObject aux2 = new JSONObject();
		aux2 = aux.getJSONObject("data");
		LightSwitchingStrategy ls_strategy = this.lssFactory.createInstance(aux);
		
		JSONObject aux3 = new JSONObject();
		aux3 = data.getJSONObject("dq_strategy");
		JSONObject aux4 = new JSONObject();
		aux4 = aux3.getJSONObject("data");
		DequeingStrategy dq_strategy = this.dqsFactory.createInstance(aux3);
		
		NewJunctionEvent nje = new NewJunctionEvent(t, id, ls_strategy, dq_strategy, x, y);
		return nje;
	}

}
