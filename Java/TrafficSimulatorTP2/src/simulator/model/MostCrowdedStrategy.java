package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy{

	private int timeSlot;
	
	public MostCrowdedStrategy(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		int pos = 0;
		int lenght = -1;
		int pos_cola = -1;
		// TODO Auto-generated method stub
		if(roads.isEmpty())
			return -1;
		if(currGreen == -1) {
			
			
			for(List<Vehicle> aux : qs) {
				if(aux.size() > lenght) {
					pos_cola = pos;
					lenght = aux.size();
				}
				pos++;
			}
			return pos_cola;
		}
		if(currTime - lastSwitchingTime < timeSlot)
			return currGreen;
		
		boolean ok = true;
		
		for(List<Vehicle> aux : qs) {
			if(aux.size() > lenght) {
				pos_cola = pos;
				lenght = aux.size();
			}
			else if(aux.size() == lenght && pos > currGreen && ok) {
				pos_cola = pos;
				lenght = aux.size();
				ok = false;
			}
			pos++;
		}
		
		return pos_cola;
	}

}
