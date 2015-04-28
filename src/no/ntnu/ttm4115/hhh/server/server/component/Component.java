package no.ntnu.ttm4115.hhh.server.server.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public float CurrentTemperature;
	public float DesiredTemperature;
	public java.lang.String Status;

	public Parameters MQTTSetup() {
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("hhh/termostats");
		m.addSubscribeTopic("hhh/mobile");
		Parameters p = new Parameters(m);
		return p;
	}

	public void msgHandler(MQTTMessage m) {
		if (m.getTopic().equals("hhh/termostats")) {
			String[] message = new String(m.getPayload()).split("\\s+");
			if (message[0].equals("Current:")) {
				this.CurrentTemperature = Float.valueOf(message[1]); 
			} else if (message[0].equals("Desired:")) {
				this.DesiredTemperature = Float.valueOf(message[1]);
			}
		} else if (m.getTopic().equals("hhh/mobile")) {
			
		}

	    System.out.println("---------- Received Message ----------");
	    System.out.println("Sent to topic: " + m.getTopic());
	    System.out.println("Payload: " + new String(m.getPayload()));
	    System.out.println("--------------------------------------");
	}

	public MQTTMessage createMessage() {
		String payload = "Desired:"+" "+String.valueOf(CurrentTemperature);
		byte[] bytes = payload.getBytes();
	    String topic = "hhh/termostats";
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

}
