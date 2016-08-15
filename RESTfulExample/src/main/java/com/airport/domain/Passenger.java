package com.airport.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="persons")
public class Passenger {
	private String lastName1;
	private String lastName2;
	private String firstName;
	private String middleName;
	public String getLastName1() {
		return lastName1;
	}
	public void setLastName1(String lastName1) {
		this.lastName1 = lastName1;
	}
	public String getLastName2() {
		return lastName2;
	}
	public void setLastName2(String lastName2) {
		this.lastName2 = lastName2;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getCompleteName() {
		String temp = this.firstName;
		if (middleName!=null)
			temp = temp+ " "+this.middleName;
		temp = temp+" "+this.lastName1+" "+this.lastName2;
		return temp;
	}
	
	

}
