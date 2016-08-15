package com.airport.rest.external;

import java.util.List;

import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Flight;
import com.airport.domain.FlightSearch;

public interface ReservationGateway {
	
	public List<Flight> getFlights(FlightSearch flightSearch);
	
	public String generateBooking(List<Flight> flights,Integer nroAdults);
	
	public Booking getBooking(String code);
	
	public List<BoardingPass> checkIn(CheckInInformation checkInInfo); 
	
	public List<Flight> getCurrentFlights();
	

}
