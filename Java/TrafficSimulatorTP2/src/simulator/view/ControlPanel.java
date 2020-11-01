package simulator.view;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileSystemView;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.SetContClassEvent;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimulator;
import simulator.model.Vehicle;
import simulator.model.Weather;

public class ControlPanel extends JPanel implements TrafficSimObserver{

	private static final long serialVersionUID = 1L;
	private Controller controller;
	private JButton cargar, cambiarContaminacion, exit, condicionesAtm, 
	run, stop;
	private SpinnerNumberModel Delay, co, we;
	private JSpinner spinnerD, spin;
	private List<Event> eventos;
	private RoadMap rmap;
	volatile Thread vol = null;
	private boolean _stopped = false;
	private JToolBar toolbar;
	int w;
	
	public ControlPanel(Controller _ctrl) {
		this.controller = _ctrl;
		_ctrl.addObserver(this);
		initGUI();
	}

	public void initGUI() {
		
		this.toolbar = new JToolBar();
		this.setLayout(new BorderLayout());
		
		//boton cargar
		this.cargar = new JButton();
		this.cargar.setIcon(new ImageIcon("resources/icons/open.png"));
		this.cargar.setToolTipText("Cargar fichero");
		this.cargar.addActionListener(new LoadListener());
		toolbar.add(this.cargar);
		//fin boton cargar
		
		toolbar.addSeparator();
		
		//boton cambiarCO2
		this.cambiarContaminacion = new JButton();
		this.cambiarContaminacion.setIcon(new ImageIcon("resources/icons/co2class.png"));
		this.cambiarContaminacion.setToolTipText("Cambiar CO2");
		this.cambiarContaminacion.addActionListener(new ChangeCO2Listener());
		toolbar.add(this.cambiarContaminacion);
		//fin boton cambiarCO2
		
		//toolbar.addSeparator();
		
		//boton weather
		this.condicionesAtm = new JButton();
		this.condicionesAtm.setIcon(new ImageIcon("resources/icons/weather.png"));
		this.condicionesAtm.setToolTipText("Cambiar cond.atm");
		this.condicionesAtm.addActionListener(new ChangeWeatherListener());
		toolbar.add(this.condicionesAtm);
		//fin boton weather
		
		toolbar.addSeparator();
		
		//boton run
		this.run = new JButton();
		this.run.setIcon(new ImageIcon("resources/icons/run.png"));
		this.run.setToolTipText("Run");
		this.run.addActionListener(new RunListener());
		toolbar.add(this.run);
		//fin boton run
		
		//toolbar.addSeparator();
		
		//boton stop
		this.stop = new JButton();
		this.stop.setIcon(new ImageIcon("resources/icons/stop.png"));
		this.stop.setToolTipText("Stop");
		this.stop.addActionListener(new StopListener());
		toolbar.add(this.stop);
		//fin boton stop
		
		//toolbar.addSeparator();
		
		//ticks
		toolbar.add(new JLabel("Ticks:"), BorderLayout.WEST);
		Delay = new SpinnerNumberModel(1, 0, 1000, 1);
		spinnerD = new JSpinner(Delay);
		spinnerD.setMaximumSize(new Dimension(70, 70));
		toolbar.add(spinnerD);
		//fin ticks
		
		toolbar.add(new JPanel());
		
		//boton exit
		this.exit = new JButton();
		this.exit.setIcon(new ImageIcon("resources/icons/exit.png"));
		this.exit.setToolTipText("Exit");
		this.exit.addActionListener(new ExitListener());
		toolbar.add(this.exit);
		//fin boton exit
		
		
		toolbar.setVisible(true);
		// fin creacion tolBar
		this.add(toolbar);
		this.setVisible(true);
	}
	

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventos = new ArrayList<Event>(events);
				rmap = new RoadMap();
				rmap = map;
			}
		});
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				eventos = new ArrayList<Event>(events);
				rmap = new RoadMap();
				rmap = map;
			}
		});
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		this.eventos.add(e);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		this.eventos.clear();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		this.eventos = new ArrayList<Event>();
		this.rmap = new RoadMap();
		
		for(Event e : events) {
			this.eventos.add(e);
		}
		
		this.rmap = map;
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
	}

	public void fileLoad() throws FileNotFoundException {
		File selectedFile = null;
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		
		int returnValue = jfc.showOpenDialog(null);
		// int returnValue = jfc.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
		}
		
		//para controlar que el archivo que le llega no es null o que no se ha pulsado la opción de cancelar
		if(selectedFile == null) jfc.cancelSelection();
		else {
			
			FileInputStream in = new FileInputStream(selectedFile);
			controller.reset();
			controller.loadEvents(in);
		}
	}
	
	
	private void run_sim(int n) {
		if ( n > 0 && !_stopped) {
			try {
				controller.run(1, null);
			
			} catch (Exception e ) {
			// TODO show error message
				JFrame frame = new JFrame();
				JOptionPane.showMessageDialog(frame, "No se ha podido lanzar el programa", "Launch Error",
						JOptionPane.ERROR_MESSAGE);
				
				stop();
				return;
			}
			SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				run_sim(n - 1);
				}
			});
	} else {
		enableToolBar(true);
		stop();
	}
}
	
	private void enableToolBar(boolean b) {
		// TODO Auto-generated method stub
		this.cargar.setEnabled(b);
		this.cambiarContaminacion.setEnabled(b);
		this.exit.setEnabled(b);
		this.condicionesAtm.setEnabled(b);
		this.run.setEnabled(b);
		this.spinnerD.setEnabled(b);
	}

	private void stop() {
		_stopped = true ;
	}
		
	public class LoadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			try {
				fileLoad();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	
	public class ChangeCO2Listener extends JDialog implements ActionListener{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//cogemos los vehiculos que hay en la lista
			List<String> list = new ArrayList<String>();
			
			for(Vehicle v: rmap.getVehicles()) {
				list.add(v.getId());
			}
			//aniadimos los vehiculos al jcombobox
			JComboBox<String> vbox=new JComboBox<String>();
			for(String a: list) {
				vbox.addItem(a);
			}
			
			//cogemos el co2
			Integer[] co2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
			//lo aniadimos al JComboBox
			JComboBox<Integer> co2box = new JComboBox<Integer>();
			for(int i = 0; i < 11; i++){
				co2box.addItem(co2[i]);
			}
			
			JDialog marco = new JDialog();
			marco.setSize(500, 200);
			marco.setTitle("ChangeCO2Class");
			//panel que va a contener todo
			JPanel principal = new JPanel();
			principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
			
			JPanel text = new JPanel();
			JTextField jtext = new JTextField();
			jtext.setText("Programa un evento para que cambie el CO2 de un veh�culo despu�s de N ticks");
			jtext.setVisible(true);
			text.add(jtext);
			principal.add(text);
			
			//JPanel de los vehiulos-----------------------------------------------------------
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.CENTER));

			JLabel l1= new JLabel(" Vehiculos ");
			p.add(l1);
			vbox.setMaximumSize(new Dimension(80,50));
			p.add(vbox);
			//Fin JPanel vehiculos---------------------------------------------------------
			
			//JPanel CO2-------------------------------------------------------------
			JLabel l2= new JLabel(" CO2 ");
			p.add(l2);
			co2box.setMaximumSize(new Dimension(80,50));
			p.add(co2box);
			//Fin JPanel CO2---------------------------------------------------------
			
			//JPanel ticks------------------------------------------------------------
			co = new SpinnerNumberModel(1, 0, 1000, 1);
			spin = new JSpinner(co);
			spin.setMaximumSize(new Dimension(80, 50));
			

			JLabel l3= new JLabel(" Ticks ");
			p.add(l3);
			p.add(spin);
			
			principal.add(p);

			//Fin JPanel ticks---------------------------------------------------------
			JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER));
			JButton aceptar = new JButton("Aceptar");
			aceptar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					marco.dispose();
					String v = (String) vbox.getSelectedItem();
					int w = (int) co2box.getSelectedItem();
					int t = (int) co.getNumber().longValue() + controller.getSim().getTime();
					
					List<Pair<String, Integer>> l = new ArrayList<Pair<String, Integer>>();
					Pair<String, Integer> n = new Pair<String, Integer>(v, w);
					l.add(n);
					Event en = new SetContClassEvent(t, l);
					controller.addEvent(en);
				}
				
			});
			JButton cancelar = new JButton("Cancelar");
			cancelar.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					marco.dispose();
				}
				
			});
			
			botones.add(aceptar);
			botones.add(cancelar);
			principal.add(botones);
			
			marco.add(principal);
			
			marco.setVisible(true);
			
		
				
		}
		
	}
	

	public class ChangeWeatherListener extends JDialog implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//cogemos los vehiculos que hay en la lista
			List<String> list = new ArrayList<String>();
			//RoadMap mapa = new RoadMap();
			for(Road r: rmap.getRoads()) {
				list.add(r.getId());
			}
			//aniadimos los vehiculos al jcombobox
			JComboBox<String> rbox=new JComboBox<String>();
			for(String a: list) {
				rbox.addItem(a);
			}
			
			//cogemos el tiempo
			
			String[] weather = {"SUNNY", "CLOUDY", "RAINY", "WINDY", "STORM"};
			//lo aniadimos al JComboBox
			JComboBox<String> wbox = new JComboBox<String>();
			for(int i = 0; i < 5; i++){
				wbox.addItem(weather[i]);
			}
			JDialog marco = new JDialog();
			marco.setSize(500, 200);
			
			//panel principal que va a tener todo
			JPanel principal = new JPanel();
			principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
			
			JPanel text = new JPanel();
			JTextField jtext = new JTextField();
			jtext.setText("Evento para cambiar las condiciones atmosfericas de una carretera despues de N ticks");
			jtext.setVisible(true);
			text.add(jtext);
			principal.add(text);

			//JPanel de los vehiculos-----------------------------------------------------------
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.CENTER));

			JLabel l1= new JLabel("Roads");
			p.add(l1);
			rbox.setMaximumSize(new Dimension(80,50));
			p.add(rbox);
			//Fin JPanel vehiculos---------------------------------------------------------
			
			//JPanel CO2-------------------------------------------------------------
			JLabel l2= new JLabel("Weather");
			p.add(l2);
			wbox.setMaximumSize(new Dimension(80,50));
			p.add(wbox);
			//Fin JPanel CO2---------------------------------------------------------
			
			//JPanel ticks------------------------------------------------------------
			we = new SpinnerNumberModel(1, 0, 1000, 1);
			spin = new JSpinner(we);
			spin.setMaximumSize(new Dimension(80, 50));
			

			JLabel l3= new JLabel("Ticks");
			p.add(l3);
			p.add(spin);
			
			JButton aceptar = new JButton("Aceptar");
			JButton cancelar = new JButton("Cancelar");
			JPanel j = new JPanel(new FlowLayout(FlowLayout.CENTER));
			
			j.add(aceptar);
			j.add(cancelar);
			
			p.add(j);
			
			aceptar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					marco.dispose();
					String v = (String) rbox.getSelectedItem();
					String w = (String) wbox.getSelectedItem();
					Weather wea = Weather.valueOf(w);
					int t = (int) we.getNumber().longValue() + controller.getSim().getTime();
					List<Pair<String, Weather>> l = new ArrayList<Pair<String, Weather>>();
					Pair<String, Weather> n = new Pair<String, Weather>(v, wea);
					l.add(n);
					Event en = new SetWeatherEvent(t, l);
					controller.addEvent(en);
				}
				
			});
			
			cancelar.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					marco.dispose();
				}
				
			});
			//Fin JPanel ticks---------------------------------------------------------
			principal.add(p);
			marco.add(principal);
			marco.setVisible(true);
				
		}
		
	}
	
	public class RunListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			_stopped = false;
			enableToolBar(false);
			run_sim((int) Delay.getNumber().longValue());
		}
	}
	
	public class DelayListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class StopListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			enableToolBar(true);
			stop();
		}
		
	}
	
	public class ExitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int res = JOptionPane.showConfirmDialog(null, "Seguro que quiere cerrar?", "Exit",
					JOptionPane.YES_NO_CANCEL_OPTION); //JOptionPane se pone en j
			
			if(res == JOptionPane.YES_OPTION) 
				System.exit(0);
			
		}
		
	}
	
	public void enableButtons() {
		this.exit.setEnabled(true);
		this.cambiarContaminacion.setEnabled(true);
		this.cargar.setEnabled(true);
		this.condicionesAtm.setEnabled(true);
		this.run.setEnabled(true);
		this.spinnerD.setEnabled(true);
		this.stop.setEnabled(true);
	}
	
	public void disableButtons() {
		this.exit.setEnabled(false);
		this.cambiarContaminacion.setEnabled(false);
		this.cargar.setEnabled(false);
		this.condicionesAtm.setEnabled(false);
		this.run.setEnabled(false);
		this.spinnerD.setEnabled(false);
	}
}
