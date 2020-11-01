package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetContClassEvent extends Event{

	private List<Pair<String, Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String, Integer>> cs) {
		super(time);
		this.cs = cs;
		
		if(cs == null) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	void execute(RoadMap map) {
		
		for(Pair<String,Integer> pair : cs) {
			Vehicle v = map.getVehicle(pair.getFirst());
			
			//r.ambCond = pair.getSecond();
			
			if(v == null) {
				throw new IllegalArgumentException();
			}
			else {
				v.setContaminationClass(pair.getSecond());
			}
		}
		
	}
	public String toString() {
		
		String s = "Change CO2 Class: ";
		
		for(int i = 0; i < this.cs.size(); i++) {
			s += "[(" + this.cs.get(i).getFirst() + "," + this.cs.get(i).getSecond() + ")], ";
		}
		
		return s;
		
		//return "Change CO2 Class: [(" + this.cs.get(0).getFirst() + "," + this.cs.get(0).getSecond() + ")]";
		
	}

}
