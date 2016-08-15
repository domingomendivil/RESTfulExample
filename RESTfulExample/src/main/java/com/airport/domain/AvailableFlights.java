package com.airport.domain;

import java.util.List;

public class AvailableFlights {
	private List<Flight> departureFlights;
	private List<Flight> returnFlights;
	public List<Flight> getReturnFlights() {
		return returnFlights;
	}
	public void setReturnFlights(List<Flight> returnFlights) {
		this.returnFlights = returnFlights;
	}
	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}
	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}
	

}
