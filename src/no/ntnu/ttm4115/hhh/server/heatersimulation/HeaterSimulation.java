package no.ntnu.ttm4115.hhh.server.heatersimulation;



import no.ntnu.item.arctis.runtime.Block;

public class HeaterSimulation extends Block {

	public float CurrentTemperature = 0;

	public float createTemp(float DesiredTemperature) {
		    
		CurrentTemperature = CurrentTemperature + (DesiredTemperature-CurrentTemperature);
			
		return CurrentTemperature;
	     }
	
	 public void stop() {
		 
	}

}
