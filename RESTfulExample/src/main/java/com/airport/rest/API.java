package com.airport.rest;

import java.util.List;

import com.airport.domain.AccountInformation;
import com.airport.domain.Airport;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Country;
import com.airport.domain.Flight;
import com.airport.domain.FlightSearch;
import com.airport.domain.Payment;
import com.airport.domain.Seat;
import com.airport.security.NotAuthorizedException;

import twitter4j.auth.Authorization;

/**
 * Public interface to access the system operations.
 * @author Domingo Mendivil
 *
 */
public interface API {

	/**
	 * 
	 * @param code
	 * @param lastName
	 * @return
	 * @throws APIException 
	 */
	public boolean cancelBooking(String code, String lastName) throws APIException;
	
	/**
	 * 
	 * @param code
	 * @param lastName
	 * @return
	 * @throws APIException
	 */
	public Booking getBooking(String code,String lastName) throws APIException;

	/**
	 * 
	 * @param checkIn
	 * @return
	 * @throws APIException
	 */
	public List<BoardingPass> checkIn(CheckInInformation checkIn) throws APIException;
	
	/**
	 * 
	 * @param beginLetters
	 * @return
	 * @throws APIException
	 */
	public List<Airport> getAirports(String beginLetters) throws APIException;
	
	/**
	 * 
	 * @return
	 */
	public List<Country> getCountries();
	
	/**
	 * 
	 * @param flightId
	 * @return
	 * @throws APIException
	 */
	public List<Seat> getSeats(Integer flightId) throws APIException;
	
	
	/**
	 * 
	 * @param payment
	 * @throws APIException
	 */
	public void makePayment(Payment payment) throws APIException;
	
	
	public boolean signIn(String authorization);
	
	
	public void signOut(String token) throws APIException;
	
	
	public AccountInformation getAccountInformation(String authorization) throws NotAuthorizedException;
	
	
	public List<Flight> getFlights(FlightSearch flightSearch) throws APIException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
