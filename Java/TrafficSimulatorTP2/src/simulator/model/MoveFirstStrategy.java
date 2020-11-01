package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeingStrategy{

	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		// TODO Auto-generated method stub
		List<Vehicle> list = new ArrayList<Vehicle>();
		if(q != null && q.size() > 0) {
			
			Vehicle v = q.get(0);
			
			if(v != null)
				list.add(v);
		}
		
		return list;
	}

}
