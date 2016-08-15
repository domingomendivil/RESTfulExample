package com.airport.domain;

import java.util.List;

public class BookingGeneration {
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private Integer adults;
	private Integer children;
	
	private String totalPrice;
	private String totalPricexAdult;
	
	
	public String getTotalPricexAdult() {
		return totalPricexAdult;
	}
	public void setTotalPricexAdult(String totalPricexAdult) {
		this.totalPricexAdult = totalPricexAdult;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	private List<Flight> departureFlights;
	private List<Flight> returnFlights;
	
	public List<Flight> getDepartureFlights() {
		return departureFlights;
	}
	public void setDepartureFlights(List<Flight> departureFlights) {
		this.departureFlights = departureFlights;
	}
	public List<Flight> getReturnFlights() {
		return returnFlights;
	}
	public void setReturnFlights(List<Flight> returnFlights) {
		this.returnFlights = returnFlights;
	}
	public Integer getAdults() {
		return adults;
	}
	public void setAdults(Integer adults) {
		this.adults = adults;
	}
	public Integer getChildren() {
		return children;
	}
	public void setChildren(Integer children) {
		this.children = children;
	}
	
	

}
