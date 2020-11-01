package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;
import simulator.view.TrafficSimObserver;

public class Controller {
	private TrafficSimulator simulator;
	private Factory<Event> eventsFactory;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) {
		
		if(sim != null && eventsFactory != null) {
			this.simulator = sim;
			this.eventsFactory = eventsFactory;
		}
		else 
			throw new IllegalArgumentException();
	}
	
	public void loadEvents(InputStream in) {
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray ja = new JSONArray();
		
		ja = jo.getJSONArray("events");
		
		for (int i = 0; i < ja.length(); i++) {
			
			JSONObject jo_evento = new JSONObject();
			jo_evento = ja.getJSONObject(i);
			
			Event evento = this.eventsFactory.createInstance(jo_evento);
			this.simulator.addEvent(evento);
		}
		
	}
	
	public TrafficSimulator getSim() {
		return this.simulator;
	}
	
	public void run(int n, OutputStream out) throws Exception {

		if(out == null) {
			out = System.out;	
		}
		
		PrintStream p = new PrintStream(out);
		p.println("{");
		p.println("  \"states\": [");

		int i = n;
		for(; i > 1; i--){
			simulator.advance();
			p.println(simulator.report() + ",");
			//p.println(",");
		}
		if(i > 0){
			simulator.advance();
			p.println(simulator.report());
		}

		p.println("]");
		p.println("}");

	}
	
	public void reset() {
		this.simulator.reset();
	}
	
	public void addObserver(TrafficSimObserver o) {
		simulator.addObserver(o);
	}
	
	public void removeObserver(TrafficSimObserver o) {
		simulator.removeObserver(o);
	}
	
	public void addEvent(Event e) {
		simulator.addEvent(e);
	}
}
