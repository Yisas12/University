package simulator.view;

import java.util.ArrayList;

import java.util.List;

import javax.swing.SwingUtilities;

import javax.swing.table.AbstractTableModel;


import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columns= {"id", "green", "queues"};
	private List<Junction> lista;
	
	public JunctionsTableModel(Controller _ctrl) {
		// TODO Auto-generated constructor stub
		this.lista = new ArrayList<Junction>();
		_ctrl.addObserver(this);
		
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		// TODO Auto-generated method stub
		return this.columns[columnIndex];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.lista.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		if(columnIndex == 1) {
			int n = lista.get(rowIndex).getGreenSemIndex();
			
			if(n != -1) return lista.get(rowIndex).getEntryRoads().get(n);
			
			return"NONE";
			
		}
		else if(columnIndex == 2) {
			if(lista.get(rowIndex).getEntryRoads().isEmpty())return " ";
			else return lista.get(rowIndex).getEntryRoads();
		}
		
		else return lista.get(rowIndex);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

	}

	public void update(List<Junction> j) {
		lista = j;
		fireTableStructureChanged();
	}
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map.getJunctions());
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map.getJunctions());
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		update(map.getJunctions());
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//lista.clear();
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
				lista = new ArrayList<>(map.getJunctions());
				fireTableStructureChanged();
			}});
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
