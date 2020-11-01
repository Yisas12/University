package simulator.model;

public class InterCityRoad extends Road{

	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int lenght,
			Weather wheater) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, lenght, wheater);
		// TODO Auto-generated constructor stub
	}

	void reduceTotalContamination() {
		int x = 0;
		
		if(this.getAmbCond().equals(Weather.SUNNY))
			x = 2;
		else if(this.getAmbCond().equals(Weather.CLOUDY))
			x = 3;
		else if(this.getAmbCond().equals(Weather.RAINY))
			x = 10;
		else if(this.getAmbCond().equals(Weather.WINDY))
			x = 15;
		else if(this.getAmbCond().equals(Weather.STORM))
			x = 20;
		
		this.totalPollution = (int) (((100.0-x)/100.0)*this.totalPollution);
			
	}
	
	void updateSpeedLimit() {
		if(this.totalPollution > this.pollutionAlarm)
			this.actLimitSpeed = (int) (this.maxSpeed*0.5);
		else
			this.actLimitSpeed = this.maxSpeed;
	}
	
	int calculateVehicleSpeed(Vehicle v) { 
		if(v.getState() != VehicleStatus.WAITING) {
			if(this.getAmbCond().equals(Weather.STORM))
				return (int) (this.actLimitSpeed*0.8);
			else
			return this.actLimitSpeed;
			}
		else return 0;
	}
}
