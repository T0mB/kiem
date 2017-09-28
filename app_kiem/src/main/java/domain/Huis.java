package main.java.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Huis {
	
	private int id;
	private List<Sensor> hasSensors = new ArrayList<Sensor>();
	private List<Recording> hasRecordings = new ArrayList<Recording>();
	
	
	public Huis(int s){
		id = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
