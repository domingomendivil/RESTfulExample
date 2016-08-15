package com.airport.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="flights")
public class Flight {

	
	private String price;
	private String type;
	
	@Id
	private String number;
	
	@ManyToOne
	private Airport fromAirport;
	
	@ManyToOne
	private Airport toAirport;
	
	private Date fromDate;
	
	private Date toDate;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	private String stops;
	public String getStops() {
		return stops;
	}
	public void setStops(String stops) {
		this.stops = stops;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public Airport getFromAirport() {
		return fromAirport;
	}
	
	public void setFromAirport(Airport from) {
		this.fromAirport = from;
	}
	
	public Airport getToAirport() {
		return toAirport;
	}
	
	public void setToAirport(Airport to) {
		this.toAirport = to;
	}
	
	
	
	

}
