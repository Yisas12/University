package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchingStrategy>{

	RoundRobinStrategyBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public RoundRobinStrategyBuilder() {
		super("round_robin_lss");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		
		int t = 1;
		
		if(data.has("timeslot")) {
			t = data.getInt("timeslot");
		}
		
		RoundRobinStrategy rrs = new RoundRobinStrategy(t);
		return rrs;
	}

}
