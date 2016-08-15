package com.airport.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="bookings")
public class Booking {
	
	@Id
	private String code;
	
	@OneToMany
	private List<ETicket> eTickets;
	
	private CreditCard creditCard;
	
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	private Status status;
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	private Date createdDate;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	public List<ETicket> geteTickets() {
		return eTickets;
	}
	public void seteTickets(List<ETicket> eTickets) {
		this.eTickets = eTickets;
	}
	
	
	
	
	
}
