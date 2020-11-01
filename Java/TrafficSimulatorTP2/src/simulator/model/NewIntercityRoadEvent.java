package simulator.model;


public class NewIntercityRoadEvent extends Event{

	private String id;
	private String srcJ;
	private String destJ;
	private int maxSpeed;
	private int contLimit;
	private int length;
	private Weather weather;
	
	public NewIntercityRoadEvent(int time, String id, String srcJun, 
			String destJunc, int length, int co2limit, int maxSpeed,
			Weather weather) {
		super(time);
		this.id = id;
		this.srcJ = srcJun;
		this.destJ = destJunc;
		this.maxSpeed = maxSpeed;
		this.contLimit = co2limit;
		this.length = length;
		this.weather = weather;
	}

	@Override
	void execute(RoadMap map) throws Exception {
		InterCityRoad intercity = new InterCityRoad(id, map.getJunction(srcJ), map.getJunction(destJ), maxSpeed, contLimit, length, weather);
		map.addRoad(intercity);
		
		//hay que a�adir esta carretera a los cruces correspondientes, como entrante y saliente
		map.getJunction(this.destJ).addIncommingRoad(intercity);//aniades carretera de llegada al cruce destino
		map.getJunction(this.srcJ).addOutGoingRoad(intercity);//aniades carretera de salida de este cruce
		
	}

	@Override
	public String toString() {
	return "New InterCity '" + id + "'" ;
	}
}
