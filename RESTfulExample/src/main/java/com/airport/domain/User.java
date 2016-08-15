package com.airport.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	
	@Id
	private String email;
	

	private String password;
	private String firstName;
	
	private String lastName1;
	private String lastName2;

	
	private String phoneNumber;
	private String frequentFlyerId;
	private Date createdDate;
	private Date activatedDate; 
	private Address livingAddress;
	private boolean activated;

	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName1() {
		return lastName1;
	}
	public void setLastName(String lastName1) {
		this.lastName1 = lastName1;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getFrequentFlyerId() {
		return frequentFlyerId;
	}
	public void setFrequentFlyerId(String frequentFlyerId) {
		this.frequentFlyerId = frequentFlyerId;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getActivatedDate() {
		return activatedDate;
	}
	public void setActivatedDate(Date activatedDate) {
		this.activatedDate = activatedDate;
	}
	public Address getLivingAddress() {
		return livingAddress;
	}
	public void setLivingAddress(Address livingAddress) {
		this.livingAddress = livingAddress;
	}
	public boolean isActivated() {
		return activated;
	}
	public void setActivated(boolean activated) {
		this.activated = activated;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}	
}

