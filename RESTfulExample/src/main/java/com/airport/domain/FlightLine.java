package com.airport.domain;

import java.util.List;

public class FlightLine {
	
	private String from;
	private List<FlightPrice> flightPrices;
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<FlightPrice> getFlightPrices() {
		return flightPrices;
	}
	public void setFlightPrices(List<FlightPrice> flightPrices) {
		this.flightPrices = flightPrices;
	}
	

}
