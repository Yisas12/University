package simulator.view;

import java.util.ArrayList;

import java.util.List;

import javax.swing.SwingUtilities;

import javax.swing.table.AbstractTableModel;


import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.Vehicle;

public class VechicleTableModel extends AbstractTableModel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Vehicle> vlist;
	private String[] columns= {"id", "location", "itinerary", "CO2", "vMax", "vAct", "totalCO2", "distance"};
	
	public VechicleTableModel(Controller _ctrl) {
		// TODO Auto-generated constructor stub
		this.vlist = new ArrayList<Vehicle>();
		_ctrl.addObserver(this);
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.vlist.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		/*switch(columnIndex) {
		case 0: return vlist.get(rowIndex);
		default: return null;
		}*/
		
		if(columnIndex == 1) return vlist.get(rowIndex).getRoad() + ":" + vlist.get(rowIndex).getLocation();
		else if(columnIndex == 2) return vlist.get(rowIndex).getItinerario();
		else if(columnIndex == 3) return vlist.get(rowIndex).getPolutionGrade();
		else if(columnIndex == 4) return vlist.get(rowIndex).getMaxSpeed();
		else if(columnIndex == 5) return vlist.get(rowIndex).getCurrentSpeed();
		else if(columnIndex == 6) return vlist.get(rowIndex).getTotalPolution();
		else if(columnIndex == 7) return vlist.get(rowIndex).getTotalDistance();
		else return vlist.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if(this.getValueAt(rowIndex, columnIndex).toString().equalsIgnoreCase("CO2")) {
			return true;
		}
		
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		/*switch(columnIndex) {
		case 0: vlist.set(rowIndex, (Vehicle) aValue);
		}*/
		
		if(columnIndex == 0) vlist.set(rowIndex, (Vehicle) aValue);
		else vlist.set(columnIndex, (Vehicle) aValue);
	}
	
	public void update(List<Vehicle> v) {
		vlist = v;
		fireTableStructureChanged();
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map.getVehicles());
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map.getVehicles());
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		update(map.getVehicles());
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//vlist.clear();
				fireTableStructureChanged();
			}});
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				vlist = new ArrayList<>(map.getVehicles());
				fireTableStructureChanged();
			}});
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub	
	}

}
