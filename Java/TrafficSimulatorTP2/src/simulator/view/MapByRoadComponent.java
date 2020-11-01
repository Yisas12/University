package simulator.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.TitledBorder;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.Vehicle;
import simulator.model.VehicleStatus;
import simulator.model.Weather;

public class MapByRoadComponent extends JComponent implements TrafficSimObserver{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final int _JRADIUS = 10;
	
	private static final Color _BG_COLOR = Color.WHITE;
	private static final Color _JUNCTION_LABEL_COLOR = new Color(200, 100, 0);
	private static final Color _GREEN_LIGHT_COLOR = Color.GREEN;
	private static final Color _RED_LIGHT_COLOR = Color.RED;
	private static final Color _JUNCTION_COLOR = Color.BLUE;
	private RoadMap _map;
	private Image _car;
	private Image _cond;
	private Image _cont;
	
	public MapByRoadComponent(Controller ctrl) {
		initGUI();
		ctrl.addObserver(this);
		this.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2),
				"Map by Road", TitledBorder.LEFT, TitledBorder.TOP));
	}
	
	private void initGUI() {
		_car = loadImage("car.png");
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		// clear with a background color
		g.setColor(_BG_COLOR);
		g.clearRect(0, 0, getWidth(), getHeight());
		
		if (_map == null || _map.getRoads().size() == 0) {
			g.setColor(Color.red);
			g.drawString("No map yet!", getWidth() / 2 - 50, getHeight() / 2);
		} else {
			updatePrefferedSize();
			drawMap(g);
		}
	}
	
	private void drawMap(Graphics g) {
		int x1, x2, y, i = 0;
		for (Road r: _map.getRoads()) {
			g.setColor(Color.BLACK);
			//para pintar las lineas
			//---------------------------
			x1 = 50;
			x2 = getWidth() - 100;
			y = (i + 1)*50;
			g.drawLine(x1, y, x2, y);
			
			//----------------------------
			
			//para pintar los circulos
			//----------------------------
			Color circleColor = _RED_LIGHT_COLOR;
			g.drawString(r.getSrcJunc().getId(), x1 - _JRADIUS / 2, (y - _JRADIUS / 2) - 5);
			g.drawString(r.getDestJunc().getId(), x2 - _JRADIUS / 2, (y - _JRADIUS / 2) - 5);
			int idx = r.getDestJunc().getGreenSemIndex();
			if (idx != -1 && r.equals(r.getDestJunc().getEntryRoads().get(idx))) {
				circleColor = _GREEN_LIGHT_COLOR;
			}
			
			g.setColor(_JUNCTION_COLOR);
			g.fillOval(x1 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			g.setColor(circleColor);
			g.fillOval(x2 - _JRADIUS / 2, y - _JRADIUS / 2, _JRADIUS, _JRADIUS);
			
			//---------------------------
			
			//para pintar los coches
			//---------------------------
			drawVehicles(g, r, y);
			
			//---------------------------
			
			//Para el id de la carretera
			//----------------------
			g.setColor(Color.BLACK);
			g.drawString(r.getId(), x1 - 30, y + 5);
			//----------------------
			
			//para pintar las imagenes
			//---------------------------
			if(r.getAmbCond().equals(Weather.CLOUDY)) {
				_cond = loadImage("cloud.png");
			}
			else if(r.getAmbCond().equals(Weather.RAINY)) {
				_cond = loadImage("rain.png");
			}
			else if(r.getAmbCond().equals(Weather.STORM)) {
				_cond = loadImage("storm.png");
			}
			else if(r.getAmbCond().equals(Weather.SUNNY)) {
				_cond = loadImage("sun.png");
			}
			else if(r.getAmbCond().equals(Weather.WINDY)) {
				_cond = loadImage("wind.png");
			}
			
			//hay que moverlo y redimensionarlo xd
			g.drawImage(_cond, x2 + 15, y - 15, 32, 32, this);
			
			int A = r.getTotalPollution();
			int B = r.getPollutionAlarm();
			int c = (int) Math.floor(Math.min((double) A /(1.0 + (double) B), 1.0) / 0.19);
			
			if(c == 0) {
				_cont = loadImage("cont_0.png");
			}
			else if(c == 1) {
				_cont = loadImage("cont_1.png");
			}
			else if(c == 2) {
				_cont = loadImage("cont_2.png");
			}
			else if(c == 3) {
				_cont = loadImage("cont_3.png");
			}
			else if(c == 4) {
				_cont = loadImage("cont_4.png");
			}
			else if(c == 5){
				_cont = loadImage("cont_5.png");
			}
			
			//hay que moverlo y redimensionarlo tambien
			g.drawImage(_cont, x2 + 55, y - 15, 32, 32, this);
			//_----------------------------
			i++;
		}
	}
	
	private void drawVehicles(Graphics g, Road r, int y) {
		int i = 0;
		for(Vehicle v : r.getVehicles()) {
			if (v.getState() != VehicleStatus.ARRIVED) {
				int x1 = 50;
				int x2 = getWidth() - 100;
				
				int pos = v.getLocation();
				int len = r.getLenght();
				
				int x = x1 + (int) ((x2 - x1)* ((double) pos / (double) len));
				
	
				// draw an image of a car (with circle as background) and it identifier
				
				g.drawImage(_car, x, y - 6, 12, 12, this);
				g.drawString(v.getId(), x, y - 6);
				i++;
			}
		}
	}
	
	private void updatePrefferedSize() {
		int maxW = 200;
		int maxH = 200;
		for (Junction j : _map.getJunctions()) {
			maxW = Math.max(maxW, j.getX());
			maxH = Math.max(maxH, j.getY());
		}
		maxW += 20;
		maxH += 20;
		setPreferredSize(new Dimension(maxW, maxH));
		setSize(new Dimension(this.getWidth(), this.getHeight()));
	}
	
	private Image loadImage(String img) {
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
	
	public void update(RoadMap map) {
		_map = map;
		repaint();
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map);
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		update(map);
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map);
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		update(map);
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
