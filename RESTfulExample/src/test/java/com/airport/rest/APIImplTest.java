package com.airport.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.ETicket;
import com.airport.domain.FlightSearch;
import com.airport.domain.Passenger;
import com.airport.payments.PaymentAPI;
import com.airport.reservations.ReservationGateway;


public class APIImplTest {
	
	
/**	private API getAPI() {
		DAO dao = (Mockito.mock(DAO.class));
		EMail email = (Mockito.mock(EMail.class));
		PaymentAPI paymentAPI = (Mockito.mock(PaymentAPI.class));
		ReservationGateway reservationGateway = (Mockito.mock(ReservationGateway.class));
		PDFPrinter printer = new PDFPrinter();
		API api = new APIImpl(dao, email,paymentAPI,reservationGateway,printer);
		return api;
	}
	
	
	@Test
	public void getBooking1(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("","");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking2(){
		DAO dao = (Mockito.mock(DAO.class));
		EMail email = (Mockito.mock(EMail.class));
		API api = new APIImpl(dao, email,null,null,null);
		boolean exception=false;
		String code= "ASD808";
		Mockito.when(dao.getbyId(Booking.class, code)).thenReturn(null);
		try {
			api.getBooking(code,"");
		} catch (APIException e) {
			exception=true;
			assertEquals("Booking not found",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	
	@Test
	public void getBooking3(){
		DAO dao = (Mockito.mock(DAO.class));
		EMail email = (Mockito.mock(EMail.class));
		API api = new APIImpl(dao, email,null,null,null);
		boolean exception=false;
		String code= "ASD808";
		Booking booking = new Booking();
		List<ETicket> eTickets = new ArrayList<ETicket>();
		ETicket eTicket = new ETicket();
		Passenger passenger = new Passenger();
		passenger.setLastName1("MENDEZ");
		eTicket.setPassenger(passenger);
		eTickets.add(eTicket);
		booking.seteTickets(eTickets);
		Mockito.when(dao.getbyId(Booking.class, code)).thenReturn(booking);
		try {
			api.getBooking(code,"PEREZ");
		} catch (APIException e) {
			exception=true;
			assertEquals("Booking not found",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking4(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("","<%%>>");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Last Name",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking5(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("<%%>","MENDEZ");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}



	@Test
	public void cancelBooking1(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("","");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test	
	public void cancelBooking2(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("","<%%>");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Last Name",e.getMessage());
		}
		assertTrue(exception);
	}

	@Test
	public void cancelBooking3(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("<%%>","MENDEZ");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	public void checkIn1(){
		API api = getAPI();
		boolean exception=false;
		try {
			CheckInInformation checkIn = new CheckInInformation();
			api.checkIn(checkIn);
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	@Test	
	public void checkIn2(){
		API api = getAPI();
		boolean exception=false;
		try {
			CheckInInformation checkIn = new CheckInInformation();
			api.checkIn(checkIn);
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test	
	public void signIn1(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.signIn("domingo.mendivil@gmail.com","");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid user name or password",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test
	public void signIn2(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.signIn("","123");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid user name or password",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test	
	public void signIn3(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.signIn("<%>","");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid user name or password",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test	
	public void signIn4(){
		API api = getAPI();
		boolean exception=false;
		try {
			api.signIn("","<%A>");
		} catch (APIException e) {
			exception=true;
			assertEquals("Invalid user name or password",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	public void getFlights1(){
		API api = getAPI();
		boolean exception=false;
		try {
			FlightSearch flightSearch = new FlightSearch();
			api.getFlights(flightSearch);
		} catch (APIException e) {
			exception=true;
			assertEquals("No flights were found",e.getMessage());
		}
		assertTrue(exception);
	}

**/


	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	




}
