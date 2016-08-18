package com.airport.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

import com.airport.dao.DAO;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.ETicket;
import com.airport.domain.FlightSearch;
import com.airport.domain.Passenger;
import com.airport.email.EmailSender;
import com.airport.exceptions.ObjectNotFoundException;
import com.airport.exceptions.OperationException;
import com.airport.payments.PaymentAPI;
import com.airport.pdf.PDFPrinter;
import com.airport.pdf.PDFPrinterImpl;
import com.airport.publicapi.PublicApi;
import com.airport.publicapi.PublicApiImpl;
import com.airport.reservations.ReservationAPI;
import com.airport.security.Authorizator;


public class APIImplTest {
	
	
	private PublicApi getAPI() {
		DAO dao = (Mockito.mock(DAO.class));
		EmailSender email = (Mockito.mock(EmailSender.class));
		PaymentAPI paymentAPI = (Mockito.mock(PaymentAPI.class));
		ReservationAPI reservationGateway = (Mockito.mock(ReservationAPI.class));
		PDFPrinter printer = new PDFPrinterImpl();
		Authorizator authorizator = (Mockito.mock(Authorizator.class));
			
		PublicApi api = new PublicApiImpl(dao, email,paymentAPI,reservationGateway,authorizator,printer);
		return api;
	}
	
	
	@Test
	public void getBooking1(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("","");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Booking code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking2(){
		DAO dao = (Mockito.mock(DAO.class));
		EmailSender email = (Mockito.mock(EmailSender.class));
		Authorizator authorizator = (Mockito.mock(Authorizator.class));
		ReservationAPI reservationGateway =  (Mockito.mock(ReservationAPI.class));
		PaymentAPI paymentApi = (Mockito.mock(PaymentAPI.class));
		PDFPrinter pdfPrinter = (Mockito.mock(PDFPrinter.class));
		PublicApi api = new PublicApiImpl(dao, email,paymentApi,reservationGateway,authorizator,pdfPrinter);
		boolean exception=false;
		String code= "ASD808";
		Mockito.when(dao.getbyId(Booking.class, code)).thenReturn(null);
		try {
			api.getBooking(code,"");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Booking not found",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	
	@Test
	public void getBooking3(){
		DAO dao = (Mockito.mock(DAO.class));
		EmailSender email = (Mockito.mock(EmailSender.class));
		Authorizator authorizator = (Mockito.mock(Authorizator.class));
		ReservationAPI reservationGateway =  (Mockito.mock(ReservationAPI.class));
		PaymentAPI paymentApi = (Mockito.mock(PaymentAPI.class));
		PDFPrinter pdfPrinter = (Mockito.mock(PDFPrinter.class));
		PublicApi api = new PublicApiImpl(dao, email,paymentApi,reservationGateway,authorizator,pdfPrinter);
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
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Booking not found",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking4(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("","<%%>>");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Last Name",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	@Test
	public void getBooking5(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.getBooking("<%%>","MENDEZ");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}



	@Test
	public void cancelBooking1(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("","");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Booking code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	@Test	
	public void cancelBooking2(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("","<%%>");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Last Name",e.getMessage());
		}
		assertTrue(exception);
	}

	@Test
	public void cancelBooking3(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			api.cancelBooking("<%%>","MENDEZ");
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	public void checkIn1(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			CheckInInformation checkIn = new CheckInInformation();
			api.checkIn(checkIn);
		} catch (OperationException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	@Test	
	public void checkIn2(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			CheckInInformation checkIn = new CheckInInformation();
			api.checkIn(checkIn);
		} catch (OperationException e) {
			exception=true;
			assertEquals("Invalid Booking Code",e.getMessage());
		}
		assertTrue(exception);
	}
	
	
	
	public void getFlights1(){
		PublicApi api = getAPI();
		boolean exception=false;
		try {
			FlightSearch flightSearch = new FlightSearch();
			api.getFlights(flightSearch);
		} catch (ObjectNotFoundException e) {
			exception=true;
			assertEquals("No flights were found",e.getMessage());
		}
		assertTrue(exception);
	}



	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	




}
