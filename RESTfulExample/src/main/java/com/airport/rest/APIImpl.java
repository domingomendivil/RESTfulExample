package com.airport.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.airport.dao.DAO;
import com.airport.domain.AccountInformation;
import com.airport.domain.Airport;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Country;
import com.airport.domain.ETicket;
import com.airport.domain.Flight;
import com.airport.domain.FlightSearch;
import com.airport.domain.Payment;
import com.airport.domain.Seat;
import com.airport.domain.Status;
import com.airport.domain.User;
import com.airport.email.EMail;
import com.airport.payments.PaymentAPI;
import com.airport.pdf.PDFPrinter;
import com.airport.reservations.ReservationGateway;
import com.airport.security.Authorizator;
import com.airport.security.NotAuthorizedException;

public class APIImpl implements API {
	
	private DAO dao;
	private EMail email;
	private PaymentAPI paymentAPI;
	private ReservationGateway reservationGateway;
	private PDFPrinter pdfPrinter;
	private Authorizator authorizator;
	
	public APIImpl(DAO dao,EMail email,PaymentAPI paymentAPI,ReservationGateway reservationGateway,Authorizator authorizator,PDFPrinter printer){
		this.dao=dao;
		this.email=email;
		this.paymentAPI=paymentAPI;
		this.reservationGateway=reservationGateway;
		this.pdfPrinter=printer;
		this.authorizator=authorizator;
	}

	@Override
	public boolean cancelBooking(String code, String lastName) {
		Booking booking = getBooking(code);
		if (verifyLastName(booking,lastName)){
			dao.begin();
			booking.setStatus(Status.cancelled);
			dao.commit();
			try {
				sendEmailCancelledBooking(booking);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}else{
			return false;
		}
	}

	@Override
	public Booking getBooking(String code, String lastName) throws APIException {
		Booking booking = getBooking(code);
		if (verifyLastName(booking, lastName)){
			return booking;	
		}else{
			throw new APIException("Booking not found");
		}
		
	}

	@Override
	public List<BoardingPass> checkIn(CheckInInformation checkIn) throws APIException {
		return reservationGateway.checkIn(checkIn);
		
	}

	@Override
	public List<Airport> getAirports(String beginLetters) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seat> getSeats(Integer flightId) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void makePayment(Payment payment) throws APIException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void signOut(String token) throws APIException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Flight> getFlights(FlightSearch flightSearch) throws APIException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void sendEmailCancelledBooking(Booking booking) throws IOException {
		String file = readFile("canceEmail");
		file.replaceAll("<%=booking.code%>",booking.getCode());
		email.sendEmail(file);
	}

	private boolean verifyLastName(Booking booking,String lastName) {
		for (ETicket eTicket:booking.geteTickets()){
			if ((eTicket.getPassenger().getLastName1()+" "+eTicket.getPassenger().getLastName2()).equals(lastName)){
				return true;
			}
		}
		return false;
	}
	
	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	
	private Booking getBooking(String code) {
		return reservationGateway.getBooking(code);
	}

	@Override
	public boolean signIn(String authorization) {
		try {
			authorizator.validateAccess(authorization);
			return true;
		} catch (NotAuthorizedException e) {
			return false;
		}
	}

	@Override
	public AccountInformation getAccountInformation(String authorization) throws NotAuthorizedException{
		authorizator.validateAccess(authorization);
		AccountInformation accountInfo = new AccountInformation();
		String userId = authorizator.getUserFromAuthorization(authorization);
		User user = (User)dao.getbyId(User.class,userId);
		accountInfo.setId(userId);
		return accountInfo;
		
	}	
	
	
	
	
	
	
	
	

}
