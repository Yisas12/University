package simulator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;


public class Junction extends SimulatedObject{

	private List<Road> entryRoads;
	private Map<Junction, Road> exitRoads;
	private List<List<Vehicle>> listRoads;
	private Map<Road,List<Vehicle>> roadQueue;
	private int greenSemIndex;
	private int lastSemPas;
	private LightSwitchingStrategy changeStratSem;
	private DequeingStrategy getElemCola;
	private int x;
	private int y;
	JSONObject jo;
	
	Junction(String id, LightSwitchingStrategy lsStrategy, DequeingStrategy 
			dqStrategy, int xCoor, int yCoor) {
		super(id);
		if(dqStrategy == null || lsStrategy == null || xCoor < 0 || yCoor < 0)
			throw new IllegalArgumentException();
		
		this.entryRoads = new ArrayList<Road>();
		this.exitRoads = new HashMap<Junction, Road>();
		this.listRoads = new ArrayList <List<Vehicle>>();
		this.roadQueue = new HashMap <Road, List<Vehicle>>();
		this.changeStratSem = lsStrategy;
		this.getElemCola = dqStrategy;
		this.setX(xCoor);
		this.setY(yCoor);
		this.lastSemPas = 0;
		this.greenSemIndex = -1;
		// TODO Auto-generated constructor stub
	}
	@Override
	void advance(int time) {
		if(this.greenSemIndex != -1) {
		//estrategia de extracci�n de cola
			List<Vehicle> list = new ArrayList<Vehicle>();
			//Road r = this.entryRoads.get(this.greenSemIndex);
			list = this.getElemCola.dequeue(this.listRoads.get(this.greenSemIndex));
			
			for(Vehicle v: list) {
				v.moveToNextRoad();
				//this.roadQueue.get(r).remove(v);
				this.listRoads.get(this.greenSemIndex).remove(v);
			}
		}
		

		//estrategia de cambio de semaforo
		int n = this.changeStratSem.chooseNextGreen(this.entryRoads, this.listRoads, this.greenSemIndex,
				this.lastSemPas, time);
		
		if(n != this.greenSemIndex) {
			this.greenSemIndex = n;
			this.lastSemPas = time;
		}
	}
	@Override
	public JSONObject report() {
		// TODO Auto-generated method stub
		jo  = new JSONObject();
		if(this.greenSemIndex != -1) {
			jo.put("green", this.entryRoads.get(this.greenSemIndex).getId());
		}
		else {
			jo.put("green", "none");
		}
		
		JSONArray array = new JSONArray();
		
		for(Road r : this.entryRoads) {
			JSONArray array_vehiculos = new JSONArray();
			JSONObject obj = new JSONObject();
			
			if(this.roadQueue.size() > 0) {
				for(Vehicle v : this.roadQueue.get(r)) {
					array_vehiculos.put(v.getId());
				}
			}
			
			obj.put("road", r.getId());
			obj.put("vehicles", array_vehiculos);
			array.put(obj);
		}
		
		jo.put("queues", array);
		jo.put("id",  _id);
		
		return jo;
	}
	
	void addIncommingRoad(Road r) throws Exception {
		//comprobar que la carretera es entrante
		if(!(r.getDestJunc().equals(this))) {
			throw new Exception();
		}
		else {
			//a�adir r a las carreteras entrantes
			this.entryRoads.add(r);
			
			//a�adir una cola al final de la lista de colas
			List<Vehicle> cola = new LinkedList<Vehicle>();
			this.listRoads.add(cola);
			
			//a�adir al mapa
			this.roadQueue.put(r, cola);
		}
	}
	
	void addOutGoingRoad(Road r) throws Exception { 
		Junction j = r.getDestJunc();
		
		//comprobar que es saliente r y que niguna carretera va desde this al cruce j
		if(r.getSrcJunc() != this || roadTo(j) != null) {
			throw new Exception();
		}
		else {
			this.exitRoads.put(j, r);
		}
	}
	
	void enter(Vehicle v) {
		this.roadQueue.get(v.getRoad()).add(v);
	}
	
	Road roadTo(Junction j) {
		return this.exitRoads.get(j);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public String getId() {
		return this._id;
	}
	
	public int getGreenSemIndex() {
		return greenSemIndex;
	}
	public void setGreenSemIndex(int greenSemIndex) {
		this.greenSemIndex = greenSemIndex;
	}
	public List<Road> getEntryRoads() {
		return entryRoads;
	}
	public void setEntryRoads(List<Road> entryRoads) {
		this.entryRoads = entryRoads;
	}
	
	/*public List<Road> getEntryRoadsSemGreen() {
		List<Road> r = null;
		Road j = null;
		
		for(int i = 0; i < this.entryRoads.size(); i++) {
			if(this.greenSemIndex == -1) {
				j = this.entryRoads.get(i).getRoad();
				r.add(j);
			}
		}
		
		return r;
		
	}*/

}
