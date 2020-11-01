package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeingStrategy;
import simulator.model.MoveAllStrategy;

public class MoveAllStrategyBuilder extends Builder<DequeingStrategy>{
	
	public MoveAllStrategyBuilder() {
		super("move_all_dqs");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DequeingStrategy createTheInstance(JSONObject data) {
		MoveAllStrategy dqs = new MoveAllStrategy();
		return dqs;
	}

}
