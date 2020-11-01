package simulator.model;

public class CityRoad extends Road{

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght, Weather wheater) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, wheater);
		// TODO Auto-generated constructor stub
	}

	@Override
	void reduceTotalContamination() {
		// TODO Auto-generated method stub
		int x;
		
		if(this.getAmbCond().equals(Weather.WINDY) || this.getAmbCond().equals(Weather.STORM))
			x = 10;
		else
			x = 2;
		
		this.totalPollution -= x;
		
		if(this.totalPollution < 0)
			this.totalPollution = 0;
	}

	@Override
	void updateSpeedLimit() {
		// TODO Auto-generated method stub
		
	}
	
	int calculateVehicleSpeed(Vehicle v) { 
		if(v.getState() != VehicleStatus.WAITING)
			return (int) (((11.0-v.getPolutionGrade())/11.0)*this.maxSpeed);
		else return 0;
	}

}
