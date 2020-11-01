package simulator.model;

public class NewJunctionEvent extends Event{

	private String id;
	private LightSwitchingStrategy light;
	private DequeingStrategy dequeue;
	private int x, y;
	
	public NewJunctionEvent(int time, String id, LightSwitchingStrategy
			lsStrategy, DequeingStrategy dqStrategy, int xCoor, int yCoor) {
		super(time);
		this.light = lsStrategy;
		this.id = id;
		this.dequeue = dqStrategy;
		this.x = xCoor;
		this.y = yCoor;
	}

	@Override
	void execute(RoadMap map) {
		Junction junct = new Junction(id, light, dequeue, x, y);
		map.addJunction(junct);
	}

	@Override
	public String toString() {
	return "New Junction '" + id + "'" ;
	}
}
