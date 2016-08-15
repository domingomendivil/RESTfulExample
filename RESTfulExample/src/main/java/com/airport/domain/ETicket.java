package com.airport.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="etickets")
public class ETicket {
	
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public ETicketStatus getStatus() {
		return status;
	}
	public void setStatus(ETicketStatus status) {
		this.status = status;
	}
	private String number;
	
	@ManyToOne
	private Booking booking;
	
	private ETicketStatus status; 
	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Passenger getPassenger() {
		return passenger;
	}
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	public List<Flight> getFlights() {
		return flights;
	}
	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	public TicketType getTicketType() {
		return ticketType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	public Date getCheckedInTime() {
		return checkedInTime;
	}
	public void setCheckedInTime(Date checkedInTime) {
		this.checkedInTime = checkedInTime;
	}
	public String getSelectedSeat() {
		return selectedSeat;
	}
	public void setSelectedSeat(String selectedSeat) {
		this.selectedSeat = selectedSeat;
	}
	public BoardingPass getBoardingPass() {
		return boardingPass;
	}
	public void setBoardingPass(BoardingPass boardingPass) {
		this.boardingPass = boardingPass;
	}
	private Passenger passenger;
	
	private List<Flight> flights;
	
	private TicketType ticketType;
	
	private Date checkedInTime;
	private String selectedSeat;
	private BoardingPass boardingPass;
	
	
}
