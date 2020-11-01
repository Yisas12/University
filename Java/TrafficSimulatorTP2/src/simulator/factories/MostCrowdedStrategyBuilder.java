package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchingStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy>{
	
	public MostCrowdedStrategyBuilder() {
		super("most_crowded_lss");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) {
		int t = 1;
		
		if(data.has("timeslot")) {
			t = data.getInt("timeslot");
		}
		
		MostCrowdedStrategy mcs = new MostCrowdedStrategy(t);
		return mcs;
	}

}
