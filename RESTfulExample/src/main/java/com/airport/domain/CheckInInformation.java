package com.airport.domain;

import java.util.List;

import com.airport.domain.CheckedSeat;

public class CheckInInformation {
	public String getBookingCode() {
		return bookingCode;
	}

	public void setBookingCode(String bookingCode) {
		this.bookingCode = bookingCode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<CheckedSeat> getCheckedSeats() {
		return checkedSeats;
	}

	public void setCheckedSeats(List<CheckedSeat> checkedSeats) {
		this.checkedSeats = checkedSeats;
	}

	private String bookingCode;
	private String lastName;
	
	private List<CheckedSeat> checkedSeats;
	

}
