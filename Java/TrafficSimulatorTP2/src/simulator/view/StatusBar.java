package simulator.view;



import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;

public class StatusBar extends JPanel implements TrafficSimObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JToolBar statusBar;
	private JLabel eventAdded;
	private JLabel ciclos;
	
	public StatusBar(Controller _ctrl) {
		this.controller = _ctrl;
		controller.addObserver(this);
		initGUI();
	}
	
	public void initGUI() {
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.setBorder(BorderFactory.createBevelBorder(1));
		
		this.eventAdded = new JLabel();
		this.ciclos = new JLabel("Time: 0");
		this.statusBar = new JToolBar();
		this.statusBar.add(ciclos);
		this.statusBar.addSeparator();
		this.add(eventAdded);
	
		
		this.add(statusBar);
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		ciclos.setText("Time: " + time);
	}
	
	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		ciclos.setText("Time: " + time);
		eventAdded.setText("Event added: " + events.toString());
	}
	
	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
	}

}
