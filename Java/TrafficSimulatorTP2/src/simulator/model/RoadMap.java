package simulator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	private List<Junction> crList;
	private List<Road> roadList;
	private List<Vehicle> vList;
	private Map<String, Junction> crMap;
	private Map<String, Road> roadsMap;
	private Map<String, Vehicle> vMap;
	JSONObject jo;
	
	public RoadMap() {
		this.crList = new ArrayList<Junction>();
		this.roadList = new ArrayList<Road>();
		this.vList = new ArrayList<Vehicle>();
		this.crMap = new HashMap<String,Junction>();
		this.roadsMap = new HashMap<String,Road>();
		this.vMap = new HashMap<String,Vehicle>();
	}
	
	void addJunction(Junction j) { 
		if(this.getJunction(j.getId()) == null) { //si no esta ya en el mapa
			this.crList.add(j);
			this.crMap.put(j.getId(), j);
		}
	}
	
	void addRoad(Road r) { 
		if(this.getRoad(r.getId()) == null) {
			this.roadList.add(r);
			this.roadsMap.put(r.getId(), r);
		}
	}
	
	void addVehicle(Vehicle v) { 
		if(this.getVehicle(v.getId()) == null) {
			this.vList.add(v);
			this.vMap.put(v.getId(), v);
		}
	}
	
	public Junction getJunction(String id) {
		
		return this.crMap.get(id);
	}
	
	public Road getRoad(String id) {
		Road j = this.roadsMap.get(id);
		
		if(j != null && this.roadList.contains(j)) {
			return j;
		}
		else return null;
	}
	
	public Vehicle getVehicle(String id) {
		Vehicle j = this.vMap.get(id);
		
		if(j != null && this.vList.contains(j)) {
			return j;
		}
		else return null;
	}
	
	public List<Junction>getJunctions(){
		return Collections.unmodifiableList(new ArrayList<>(this.crList));
	}
	
	public List<Road>getRoads(){
		return Collections.unmodifiableList(new ArrayList<>(this.roadList));
	}
	
	public List<Vehicle>getVehicles(){
		return Collections.unmodifiableList(new ArrayList<>(this.vList));
	}
	
	void reset() {
		this.crList.clear();
		this.crMap.clear();
		this.roadList.clear();
		this.roadsMap.clear();
		this.vList.clear();
		this.vMap.clear();
	}
	
	public JSONObject report() { 
		jo = new JSONObject();
		JSONArray array_junction = new JSONArray();
		JSONArray array_roads = new JSONArray();
		JSONArray array_vehiculos = new JSONArray();
		
		for(Junction j : this.getJunctions()) {
			array_junction.put(j.report());
		}
		
		for(Road r : this.getRoads()) {
			array_roads.put(r.report());
		}
		
		for(Vehicle v : this.getVehicles()) {
			array_vehiculos.put(v.report());
		}
		
		jo.put("junctions", array_junction);
		jo.put("roads", array_roads);
		jo.put("vehicles", array_vehiculos);
		
		return jo;
	}
}
