package com.airport.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.airport.domain.Flight;

@Path("/staffapi")
public class RESTStaffImpl {
	
	private InternalAPI api;
	
	@GET
	@Path("/login")
	
	public String login(@HeaderParam("Authorization")String authorization){
		return api.signIn(authorization);
	}
	

	@POST
	@Path("/bookings/{booking}/{eTicket}/cancel")
	public void cancelTicket(@HeaderParam("Authorization")String authorization, @PathParam("booking") String booking,@PathParam("eTicket") String eTicket) throws InternalAPIException, NotAuthorizedException{
		api.cancelTicket(authorization, booking, eTicket);
	}
	
	@POST
	@Path("/bookings/{booking}/cancel")
	public void cancelBooking(@HeaderParam("Authorization")String authorization,String booking) throws InternalAPIException, NotAuthorizedException{
		api.cancelBooking(authorization, booking);
	}

	@GET
	@Path("/flights")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights(@HeaderParam("Authorization")String authorization) throws NotAuthorizedException{
		return api.getFlights(authorization);
	}

	


}
