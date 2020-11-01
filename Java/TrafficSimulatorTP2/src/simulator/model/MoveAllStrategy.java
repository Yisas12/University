package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveAllStrategy implements DequeingStrategy{

	@SuppressWarnings("null")
	@Override
	public List<Vehicle> dequeue(List<Vehicle> q) {
		// TODO Auto-generated method stub
		List<Vehicle> list = new ArrayList<Vehicle>();
		list = q;
		
		return list;
	}

}
