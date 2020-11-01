package simulator.view;


import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import javax.swing.table.AbstractTableModel;


import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;

public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Event> eventos;
	private String[] columns = {"Time", "Desc"};
	
	public EventsTableModel(Controller _ctrl) {
		this.eventos = new ArrayList<Event>();
		_ctrl.addObserver(this);
	}


	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columns.length;
		}

	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return this.columns[arg0];
	}

	@Override
	public int getRowCount() {
		return this.eventos.size();
	}

	
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
		
		if(arg1 == 0) {
			return eventos.get(arg0).getTime();
		}
		else {
			return eventos.get(arg0).toString();
		}
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	//REVISAR
	@Override
	public void setValueAt(Object arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
		if(arg2 == 0) eventos.set(arg1, (Event) arg0);
		else eventos.set(arg2, (Event) arg0);
		
	}

	public void update(List<Event> e) {
		eventos = e;
		fireTableStructureChanged();
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(events);
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(events);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) { //dependiendo de lo que quieras hacer
		// TODO Auto-generated method stub
		//events.add(e);
		update(events);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				//eventos.clear();
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
				eventos = new ArrayList<>(events);
				fireTableStructureChanged();
			}});
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
