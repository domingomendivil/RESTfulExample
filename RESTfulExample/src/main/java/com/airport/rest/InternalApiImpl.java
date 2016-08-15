package com.airport.rest;

import java.util.List;

import com.airport.domain.Booking;
import com.airport.domain.Flight;
import com.airport.domain.Payment;
import com.airport.domain.StaffUser;
import com.airport.rest.external.PaymentAPI;
import com.airport.rest.external.ReservationGateway;

public class InternalApiImpl implements InternalAPI {
	private DAO dao;
	private PaymentAPI paymentApi;
	private ReservationGateway reservationApi;
	
	public InternalApiImpl(DAO dao){
		this.dao=dao;
	}
	
	private void validateAccess(String authorizaton) throws NotAuthorizedException{
		String user = getUserFromAuthorization(authorizaton);
		String secret = getSecretFromAuthorization(authorizaton);
		StaffUser staffUser = (StaffUser)dao.getbyId(StaffUser.class, user);
		if (!staffUser.getSecret().equals(encrypt(secret))){
			throw new NotAuthorizedException();
		}
	}

	@Override
	public String signIn(String authorization) {
	}

	private Object encrypt(String secret) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getSecretFromAuthorization(String authorization) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getUserFromAuthorization(String authorization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void signOut(String authorization) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelTicket(String authorization, String booking, String eTicket) throws InternalAPIException, NotAuthorizedException {
		validateAccess(authorization);
	}

	@Override
	public void cancelBooking(String authorization, String booking) throws NotAuthorizedException,InternalAPIException {
		validateAccess(authorization);
		if (reservationApi.cancelBooking(booking)){
			makeRefunds(booking);
			
		}
		
	}

	private void makeRefunds(String code) {
		Booking booking =reservationApi.getBooking(code);
		Payment payment = new Payment();
		payment.setCreditCard(booking.getCreditCard());
		paymentApi.makePayment(payment);
	}

	@Override
	public List<Flight> getFlights(String authorization) throws NotAuthorizedException {
		validateAccess(authorization);
		return reservationApi.getCurrentFlights();
	}

	

}
