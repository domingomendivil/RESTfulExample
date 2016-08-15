package com.airport.domain;

import java.util.Date;

public class FlightSearch {
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Location getFromLocation() {
		return fromLocation;
	}
	public void setFromLocation(Location fromLocation) {
		this.fromLocation = fromLocation;
	}
	public Location getToLocation() {
		return toLocation;
	}
	public void setToLocation(Location toLocation) {
		this.toLocation = toLocation;
	}
	public Boolean getOneWay() {
		return oneWay;
	}
	public void setOneWay(Boolean oneWay) {
		this.oneWay = oneWay;
	}
	public Integer getAdults() {
		return adults;
	}
	public void setAdults(Integer adults) {
		this.adults = adults;
	}
	public Integer getChilds() {
		return childs;
	}
	public void setChilds(Integer childs) {
		this.childs = childs;
	}
	private Date fromDate;
	private Date toDate;
	private Location fromLocation;
	private Location toLocation;
	private Boolean oneWay;
	private Integer adults;
	private Integer childs;

}
