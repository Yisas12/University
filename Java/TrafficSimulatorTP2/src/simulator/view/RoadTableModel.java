package simulator.view;

import java.util.ArrayList;

import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;

public class RoadTableModel extends AbstractTableModel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columns = {"id", "length", "weather", "maxspeed", "speedLimit", "CO2total", "CO2Limit"};
	private List<Road> roads;
	
	public RoadTableModel(Controller _ctrl) {
		// TODO Auto-generated constructor stub
		roads = new ArrayList<Road>();
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
		return roads.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		/*switch(columnIndex) {
		case 0: return roads.get(rowIndex);
		default: return null;
		}*/
		
		if(columnIndex == 1) return roads.get(rowIndex).getLenght();
		else if(columnIndex == 2) return roads.get(rowIndex).getAmbCond();
		else if(columnIndex == 3) return roads.get(rowIndex).getActLimitSpeed();
		else if(columnIndex == 4) return roads.get(rowIndex).getActLimitSpeed();
		else if(columnIndex == 5) return roads.get(rowIndex).getTotalPollution();
		else if(columnIndex == 6) return roads.get(rowIndex).getPollutionAlarm();
		else return roads.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if(this.getValueAt(rowIndex, columnIndex).toString().equals("condClima") || this.getValueAt(rowIndex, columnIndex).toString().equals("velAct")) {
			return true;
		}
		
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	public void update(List<Road> r) {
		roads = r;
		fireTableDataChanged();
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map.getRoads());
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		update(map.getRoads());
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//roads.clear();
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
				roads = new ArrayList<>(map.getRoads());
				fireTableStructureChanged();
			}});
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
