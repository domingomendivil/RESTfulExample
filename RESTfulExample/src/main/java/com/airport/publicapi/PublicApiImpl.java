package com.airport.publicapi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import com.airport.dao.DAO;
import com.airport.domain.AccountInformation;
import com.airport.domain.Airport;
import com.airport.domain.AvailableFlights;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Country;
import com.airport.domain.ETicket;
import com.airport.domain.ETicketStatus;
import com.airport.domain.FlightSearch;
import com.airport.domain.FlightSummary;
import com.airport.domain.Payment;
import com.airport.domain.Seat;
import com.airport.domain.User;
import com.airport.email.EmailSender;
import com.airport.exceptions.OperationException;
import com.airport.exceptions.ObjectNotFoundException;
import com.airport.payments.PaymentAPI;
import com.airport.pdf.PDFPrinter;
import com.airport.reservations.ReservationAPI;
import com.airport.security.Authorizator;
import com.airport.security.NotAuthorizedException;

public class PublicApiImpl implements PublicApi {
	
	private DAO dao;
	private EmailSender email;
	private PaymentAPI paymentAPI;
	private ReservationAPI reservationGateway;
	private PDFPrinter pdfPrinter;
	private Authorizator authorizator;
	
	public PublicApiImpl(DAO dao,EmailSender email,PaymentAPI paymentAPI,ReservationAPI reservationGateway,Authorizator authorizator,PDFPrinter printer){
		this.dao=dao;
		this.email=email;
		this.paymentAPI=paymentAPI;
		this.reservationGateway=reservationGateway;
		this.pdfPrinter=printer;
		this.authorizator=authorizator;
	}

	@Override
	public boolean cancelBooking(String code, String lastName) throws ObjectNotFoundException {
		
		Booking booking = getBooking(code);
		if (verifyLastName(booking,lastName)){
			boolean cancelled= reservationGateway.cancelBooking(code);
			if (cancelled){
				sendEmailCancelledBooking(booking);
				return true;
			}else{
				return false;
			}
				
		}else{
			return false;
		}
	}

	@Override
	public Booking getBooking(String code, String lastName) throws ObjectNotFoundException {
		Booking booking = getBooking(code);
		if (verifyLastName(booking, lastName)){
			return booking;	
		}else{
			throw new ObjectNotFoundException("Booking not found");
		}
		
	}

	@Override
	public List<BoardingPass> checkIn(CheckInInformation checkIn) throws OperationException {
		return reservationGateway.checkIn(checkIn);
		
	}

	@Override
	public List<Airport> getAirports(String beginLetters) throws ObjectNotFoundException {
		return reservationGateway.getAirports(beginLetters);
	}

	@Override
	public List<Country> getCountries() throws ObjectNotFoundException {
		return reservationGateway.getCountries();
	}

	@Override
	public List<Seat> getSeats(String flightId) throws  ObjectNotFoundException {
		 return reservationGateway.getSeats(flightId);
	}

	@Override
	public void makePayment(Payment payment) throws OperationException {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void signOut(String token) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public AvailableFlights getFlights(FlightSearch flightSearch) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void sendEmailCancelledBooking(Booking booking)  {
		email.sendBookingCancelled(booking);
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
	
	
	private Booking getBooking(String code) throws ObjectNotFoundException {
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

	@Override
	public List<Booking> getBookings(String authorization) throws NotAuthorizedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlightSummary getFlightSummary(FlightSearch flightSearch) throws ObjectNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}


	public ETicket getETicket(String code, String lastName) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public BoardingPass confirmCheckIn(String code, String lastName, CheckInInformation checkInInformation) throws ObjectNotFoundException {
		Booking booking = getBooking(code);
		ETicket eTicket = getETicket(code, lastName);
		if (eTicket!=null){
			eTicket.setStatus(ETicketStatus.checkedIn);
			eTicket.setBooking(booking);
			BoardingPass boardingPass = new BoardingPass();
			boardingPass.seteTicket(eTicket);
			boardingPass.setBoardingTime(new Date());
			return boardingPass;
		}else{
			return null;
		}
	}


	
	
	
	
	
	
	
	

}
