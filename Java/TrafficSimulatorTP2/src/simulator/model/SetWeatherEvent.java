package simulator.model;

import java.util.List;

import simulator.misc.Pair;

public class SetWeatherEvent extends Event{

	private List<Pair<String, Weather>> ws;
	
	public SetWeatherEvent(int time, List<Pair<String, Weather>> ws) {
		super(time);
		this.ws = ws;
		
		if(ws == null) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	void execute(RoadMap map) {
		
		for(Pair<String,Weather> pair : ws) {
			Road r =map.getRoad(pair.getFirst());
			
			//r.ambCond = pair.getSecond();
			
			if(r == null) {
				throw new IllegalArgumentException();
			}
			else {
				r.setWeather(pair.getSecond());
			}
		}
	}
	
	public String toString() {
		
		String s = "Change weather: ";
		
		for(int i = 0; i < this.ws.size(); i++) {
			s += "[(" + this.ws.get(i).getFirst() + "," + this.ws.get(i).getSecond() + ")], ";
		}
		
		return s;
	//	return "Change Weather: [(" + this.ws.get(0).getFirst() + "," + this.ws.get(0).getSecond() + ")]";
		
	}
}
