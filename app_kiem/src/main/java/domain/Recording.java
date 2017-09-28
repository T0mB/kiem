package main.java.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Recording {
	
	private int id;
	private String data;
	private Date date;
	private List<Sensor> hasSens = new ArrayList<Sensor>();
	public Recording(int id, String data, Date date) {
		
		this.id = id;
		this.data = data;
		this.date = date;
	}
	
	public Recording(){
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
