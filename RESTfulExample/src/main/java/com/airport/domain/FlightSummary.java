package com.airport.domain;

import java.util.List;

public class FlightSummary {
	public List<String> dates;
	
	public List<String> getDates() {
		return dates;
	}

	public void setDates(List<String> dates) {
		this.dates = dates;
	}

	public List<FlightLine> getFlightLines() {
		return flightLines;
	}

	public void setFlightLines(List<FlightLine> flightLines) {
		this.flightLines = flightLines;
	}

	private List<FlightLine> flightLines;

}
