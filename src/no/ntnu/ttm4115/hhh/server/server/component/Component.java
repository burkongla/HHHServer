package no.ntnu.ttm4115.hhh.server.server.component;

import com.bitreactive.library.mqtt.MQTTConfigParam;
import com.bitreactive.library.mqtt.MQTTMessage;
import com.bitreactive.library.mqtt.robustmqtt.RobustMQTT.Parameters;

import no.ntnu.item.arctis.runtime.Block;

public class Component extends Block {

	public float CurrentTemperature;
	public float DesiredTemperature;
	public java.lang.String Status;
	public java.lang.String newTopic;
	public java.lang.String looPayload;
	
	public Parameters MQTTSetup() {
		setLooPayload("");
		MQTTConfigParam m = new MQTTConfigParam("dev.bitreactive.com");
		m.addSubscribeTopic("hhh/server");
		Parameters p = new Parameters(m);
		return p;
	}

	public void msgHandler(MQTTMessage m) {
		String[] message = new String(m.getPayload()).split("\\s+");
		if (message[0].equals("Current:")) {
			this.CurrentTemperature = Float.valueOf(message[1]);
			setLooPayload("Current: " + message[1]);
			System.out.println("Current: " + String.valueOf(this.CurrentTemperature));
			setNewTopic("hhh/mobile");
		} else if (message[0].equals("Desired:")) {
			this.DesiredTemperature = Float.valueOf(message[1]);
			setLooPayload("Desired: " + message[1]);
			System.out.println("Desired: " + String.valueOf(this.DesiredTemperature));
			setNewTopic("hhh/thermostats");
		}
	}

	public MQTTMessage createMessage() {
		String payload = getLooPayload();
		System.out.println("Payload: " + payload);
		byte[] bytes = payload.getBytes();
	    String topic = getNewTopic();
	    System.out.println("Topic: " + topic);
		MQTTMessage message = new MQTTMessage(bytes, topic);
		message.setQoS(2);
		return message;
	}

	public float getCurrentTemperature() {
		return CurrentTemperature;
	}

	public void setCurrentTemperature(float currentTemperature) {
		CurrentTemperature = currentTemperature;
	}

	public float getDesiredTemperature() {
		return DesiredTemperature;
	}

	public void setDesiredTemperature(float desiredTemperature) {
		DesiredTemperature = desiredTemperature;
	}

	public java.lang.String getStatus() {
		return Status;
	}

	public void setStatus(java.lang.String status) {
		Status = status;
	}

	public java.lang.String getNewTopic() {
		return newTopic;
	}

	public void setNewTopic(java.lang.String newTopic) {
		this.newTopic = newTopic;
	}

	public java.lang.String getLooPayload() {
		return looPayload;
	}

	public void setLooPayload(java.lang.String looPayload) {
		this.looPayload = looPayload;
	}
	
}
