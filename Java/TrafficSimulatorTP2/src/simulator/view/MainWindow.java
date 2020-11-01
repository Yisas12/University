package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.GridLayout;


import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.TrafficSimulator;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Controller _ctrl;
	
	public MainWindow(Controller ctrl){
		super ("Traffic Simulator");
		_ctrl = ctrl ;
		initGUI();
	}

	private void initGUI() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setContentPane(mainPanel);
		mainPanel.add(new ControlPanel(_ctrl), BorderLayout. PAGE_START);
		mainPanel.add(new StatusBar(_ctrl),BorderLayout. PAGE_END);
		
		JPanel viewsPanel = new JPanel(new GridLayout(1, 2));
		mainPanel.add(viewsPanel, BorderLayout.CENTER);

		JPanel tablesPanel = new JPanel();
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout. Y_AXIS));
		viewsPanel.add(tablesPanel);
		
		JPanel mapsPanel = new JPanel();
		mapsPanel.setLayout(new BoxLayout(mapsPanel , BoxLayout. Y_AXIS));
		viewsPanel.add(mapsPanel);

		// tables
		//EVENTOS-----------------------------------------------------
		JPanel eventsView = createViewPanel(new JTable(new EventsTableModel(_ctrl)), "Events");
		eventsView.setPreferredSize( new Dimension(500, 200));
		tablesPanel.add(eventsView);
		//Para el título
		eventsView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Events", TitledBorder.LEFT, TitledBorder.TOP));
		
		// TODO add other tables

		//VEHICULOS--------------------------------------------------------------
		JPanel vehiclesView = createViewPanel(new JTable(new VechicleTableModel(_ctrl)), "Vehicles");
		vehiclesView.setPreferredSize(new Dimension(500, 200));
		tablesPanel.add(vehiclesView);
		//Para el título
		vehiclesView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Vehicles", TitledBorder.LEFT, TitledBorder.TOP));

		//CARRETERAS----------------------------------------------------------------------
		JPanel roadView = createViewPanel(new JTable(new RoadTableModel(_ctrl)), "Roads");
		roadView.setPreferredSize( new Dimension(500, 200));
		tablesPanel.add(roadView);
		//Para el título
		roadView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Roads", TitledBorder.LEFT, TitledBorder.TOP));

		//JUNCTIONS--------------------------------------------------------------------
		JPanel junctionsView = createViewPanel(new JTable(new JunctionsTableModel(_ctrl)), "Junctions");
		junctionsView.setPreferredSize( new Dimension(500, 200));
		tablesPanel.add(junctionsView);
		//Para el título
		junctionsView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Junctions", TitledBorder.LEFT, TitledBorder.TOP));

		// maps
		//MAP-------------------------------------------------------------------------
		JPanel mapView = createViewPanel(new MapComponent(_ctrl), "Map");
		mapView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapView);
		
		// TODO add a map for MapByRoadComponent

		//MAP BY ROAD--------------------------------------------------------------
		JPanel mapByRoadView = createViewPanel(new MapByRoadComponent(_ctrl), "Map by Road");
		mapByRoadView.setPreferredSize(new Dimension(500, 400));
		mapsPanel.add(mapByRoadView);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	private JPanel createViewPanel(JComponent c , String title) {
		JPanel p = new JPanel(new BorderLayout());
		JScrollPane j = new JScrollPane(c);
		
		// TODO add a framed border to p with title
		j.getViewport().setBackground(Color.WHITE);
		p.add(j);
		return p;
	}
}
