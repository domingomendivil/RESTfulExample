package com.airport.rest;

import java.util.List;

import com.airport.domain.Flight;

public interface InternalAPI {
	
	
	public String signIn(String authorization);
	
	public void signOut(String authorization);
	
	public void cancelTicket(String authorization,String booking,String eTicket) throws NotAuthorizedException, InternalAPIException;
	
	public void cancelBooking(String authorization,String booking) throws NotAuthorizedException,InternalAPIException;
	
	public List<Flight> getFlights(String authorization) throws NotAuthorizedException;

}
