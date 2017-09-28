package main.java.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Sensor {
	
	private int idsensor;
	private String type;
	
	
	public Sensor(int ids, String t){
		idsensor = ids;
		type = t;
	}
	
	

	public Sensor(){
		
	}

	public int getIdsensor() {
		return idsensor;
	}

	public void setIdsensor(int idsensor) {
		this.idsensor = idsensor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
