package com.airport.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="boardingpasses")
public class BoardingPass {
	
	@Id
	private Integer id;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ETicket geteTicket() {
		return eTicket;
	}
	public void seteTicket(ETicket eTicket) {
		this.eTicket = eTicket;
	}
	public String getBoardingGate() {
		return boardingGate;
	}
	public void setBoardingGate(String boardingGate) {
		this.boardingGate = boardingGate;
	}
	public Date getBoardingTime() {
		return boardingTime;
	}
	public void setBoardingTime(Date boardingTime) {
		this.boardingTime = boardingTime;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	public Integer getSecurityNumber() {
		return securityNumber;
	}
	public void setSecurityNumber(Integer securityNumber) {
		this.securityNumber = securityNumber;
	}
	public String getFrequentFlyer() {
		return frequentFlyer;
	}
	public void setFrequentFlyer(String frequentFlyer) {
		this.frequentFlyer = frequentFlyer;
	}
	public String getBoardingTerminal() {
		return boardingTerminal;
	}
	public void setBoardingTerminal(String boardingTerminal) {
		this.boardingTerminal = boardingTerminal;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	private ETicket eTicket;
	private String boardingGate;
	private Date boardingTime;
	private String seat;
	private Integer securityNumber;
	private String frequentFlyer;
	private String boardingTerminal;
	private Flight flight;
	
	
	
}
