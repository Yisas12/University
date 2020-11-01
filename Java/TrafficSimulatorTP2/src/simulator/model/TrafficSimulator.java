package simulator.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;
import simulator.view.Observable;
import simulator.view.TrafficSimObserver;

public class TrafficSimulator implements Observable<TrafficSimObserver>{

	private RoadMap roadMap;
	private List<Event> listEvent;
	private int time = 0;
	private Comparator<Event> comp;
	private JSONObject jo;
	private List <TrafficSimObserver> observador;
	
	public TrafficSimulator() {
		
		comp = new Comparator<Event>() {
			
			@Override
			public int compare(Event e1, Event e2) {
				
				return e1.compareTo(e2);
			}
		};
		this.roadMap = new RoadMap();
		this.listEvent = new SortedArrayList<Event>(comp);
		this.time = 0;
		this.observador = new ArrayList<TrafficSimObserver>();
	}
	
	public void addEvent(Event e) {
		this.listEvent.add(e);
		this.listEvent.sort(comp);
		
		for(TrafficSimObserver t : observador) {
			t.onEventAdded(roadMap, listEvent, e, time);
		}
	}
	
	public void advance() throws Exception {
		//1
		this.time++;
		for(TrafficSimObserver t: observador) {
		t.onAdvanceStart(roadMap, listEvent, time);
		}
		//2
		List<Event> aux = new SortedArrayList<Event>(comp);
		for(Event v : this.listEvent) {
			if(this.time == v.getTime()) {
				try{
					v.execute(this.roadMap);
				}catch(Exception e) {
					onError(e.getMessage());
					throw new Exception (e);
				}
				
			}
			else {
				aux.add(v);
				aux.sort(comp);
			}
		}
		//hay que borrar los eventos que hayan sido procesados
		this.listEvent = aux;
		
		//3
		for(Junction j : this.roadMap.getJunctions()) {
			try{
				j.advance(this.time);
			}catch(Exception e) {
				onError(e.getMessage());
				throw new Exception (e);
			}
		}
		
		//4
		for(Road r : this.roadMap.getRoads()) {
			try{
				r.advance(this.time);
			}catch(Exception e) {
				onError(e.getMessage());
				throw new Exception (e);
			}
		}
		
		for(TrafficSimObserver t: observador) {
			t.onAdvanceEnd(roadMap, listEvent, time);
			}
		}
	
	public void reset() {
		this.time = 0;
		this.roadMap.reset();
		this.listEvent.clear();
		
		for(TrafficSimObserver t : observador) {
		t.onReset(roadMap, listEvent, time);
		}
	}
	
	public int getTime() {
		return this.time;
	}
	public JSONObject report() {
		jo = new JSONObject();
		jo.put("time", this.time);
		jo.put("state" , this.roadMap.report());
		
		return jo;
		
	}

	@Override
	public void addObserver(TrafficSimObserver o) {
		observador.add(o);
		
		for(TrafficSimObserver t : observador) {
			t.onRegister(roadMap, listEvent, time);
		}
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		observador.remove(o);
	}
	
	public void onError(String mensaje) {
		System.out.println(mensaje);
	}
}