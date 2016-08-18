package com.airport.publicapi;

import java.util.List;

import com.airport.domain.AccountInformation;
import com.airport.domain.Airport;
import com.airport.domain.AvailableFlights;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Country;
import com.airport.domain.ETicket;
import com.airport.domain.FlightSearch;
import com.airport.domain.FlightSummary;
import com.airport.domain.Payment;
import com.airport.domain.Seat;
import com.airport.exceptions.OperationException;
import com.airport.exceptions.ObjectNotFoundException;
import com.airport.security.NotAuthorizedException;

/**
 * Public interface to access the system operations.
 * @author Domingo Mendivil
 *
 */
public interface PublicApi {

	/**
	 * 
	 * @param code
	 * @param lastName
	 * @return
	 * @throws PublicApiException 
	 */
	public boolean cancelBooking(String code, String lastName) throws ObjectNotFoundException;
	
	/**
	 * 
	 * @param code
	 * @param lastName
	 * @return
	 * @throws PublicApiException
	 */
	public Booking getBooking(String code,String lastName) throws ObjectNotFoundException;


	public List<BoardingPass> checkIn(CheckInInformation checkIn) throws OperationException;
	

	public List<Airport> getAirports(String beginLetters) throws ObjectNotFoundException;
	

	public List<Country> getCountries() throws ObjectNotFoundException;
	
	
	
	/**
	 * 
	 * @param payment
	 * @throws PublicApiException
	 */
	public void makePayment(Payment payment) throws OperationException;
	
	
	public boolean signIn(String authorization);
	
	
	public void signOut(String token);
	
	
	public AccountInformation getAccountInformation(String authorization) throws NotAuthorizedException;
	
	
	public AvailableFlights getFlights(FlightSearch flightSearch) throws ObjectNotFoundException;
	
	
	public List<Booking> getBookings(String authorization) throws NotAuthorizedException;
	
	public FlightSummary getFlightSummary(FlightSearch flightSearch) throws ObjectNotFoundException;

	public ETicket getETicket(String code, String lastName) throws ObjectNotFoundException;

	public BoardingPass confirmCheckIn(String code, String lastName, CheckInInformation checkInInformation) throws ObjectNotFoundException;
	
	public List<Seat> getSeats(String flightId) throws ObjectNotFoundException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
