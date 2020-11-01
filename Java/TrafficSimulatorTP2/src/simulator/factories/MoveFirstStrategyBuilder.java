package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeingStrategy;
import simulator.model.MoveFirstStrategy;

public class MoveFirstStrategyBuilder extends Builder<DequeingStrategy>{

	MoveFirstStrategyBuilder(String type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
	
	public MoveFirstStrategyBuilder() {
		super("move_first_dqs");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected DequeingStrategy createTheInstance(JSONObject data) {
		MoveFirstStrategy mfs = new MoveFirstStrategy();
		return mfs;
	}

}
