package com.airport.domain;

import java.util.Date;

public class FlightPrice {
	private Date to;
	private String price;
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
