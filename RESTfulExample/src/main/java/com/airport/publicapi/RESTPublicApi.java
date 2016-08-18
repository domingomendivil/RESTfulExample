package com.airport.publicapi;

import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.airport.domain.AccountInformation;
import com.airport.domain.AirportList;
import com.airport.domain.AvailableFlights;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.BookingGeneration;
import com.airport.domain.CheckInInformation;
import com.airport.domain.Country;
import com.airport.domain.Flight;
import com.airport.domain.FlightSearch;
import com.airport.domain.FlightSummary;
import com.airport.domain.Passenger;
import com.airport.domain.Payment;
import com.airport.domain.Seat;
import com.airport.domain.Token;
import com.airport.exceptions.ObjectNotFoundException;
import com.airport.exceptions.OperationException;
import com.airport.factory.Factory;
import com.airport.security.NotAuthorizedException;


@Path("/api")
public class RESTPublicApi  {
	


	
   @Context
   private HttpServletRequest httpRequest;
	
	
	private PublicApi api = new Factory().getAPI();
	
	

	@GET
	@Path("/airports")

	public AirportList getAirports(@QueryParam("letters") String letters) throws ObjectNotFoundException{
		AirportList airportList= new AirportList();
	   airportList.setAirports(api.getAirports(letters));
		   return airportList;

	}
	

	
	

	@GET
	@Path("/bookings/{code}")
	public Booking getBooking(@PathParam("code") String code,@QueryParam("lastName") String lastName) throws ObjectNotFoundException{
		return api.getBooking(code, lastName);
	}
	
	

	@POST
	@Path("/bookings/{code}/cancel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean cancelBooking(@PathParam("code")String code, String lastName) throws ObjectNotFoundException  {
		return api.cancelBooking(code, lastName);
	}



	
	
	@GET
	@Path("/flights")
	public AvailableFlights getFlights(@QueryParam("from") String fromLocation, @QueryParam("to") String toLocation,  @QueryParam("fromDate") String fromDate,  @QueryParam("toDate") String toDate) throws ObjectNotFoundException {
		FlightSearch flightSearch = getFlighSearch();
			return api.getFlights(flightSearch);

	}
	
	
	@GET
	@Path("/flights/prices")
	public FlightSummary getFlightsPrices(@QueryParam("from") String from,@QueryParam("to") String to) throws ObjectNotFoundException {
		FlightSearch flightSearch = getFlighSearch();
		return api.getFlightSummary(flightSearch);
	}

	
	private FlightSearch getFlighSearch() {
		// TODO Auto-generated method stub
		return null;
	}





	public List<Flight> searchFlights(FlightSearch flightSearch) {
		// TODO Auto-generated method stub
		return null;
	}

	@GET
	@Path("/login")
	public boolean signIn(@HeaderParam("Authorization")String authorization) {
		return api.signIn(authorization);
	}


	
	public void signOut(String user, Token token) {
		if (validToken(user,token)){
			
		}

	}

	private boolean validToken(String user, Token token) {
		// TODO Auto-generated method stub
		return false; 
	}

	

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/bookings/generatebooking")
	public BookingGeneration generateBooking(BookingGeneration bookingGenaration) throws URISyntaxException {
		System.out.println("adults: "+bookingGenaration.getAdults());
		for (Flight f:bookingGenaration.getDepartureFlights()){
			System.out.println(f.getNumber());
			
		}
		bookingGenaration.setCode("ADD808");
		bookingGenaration.setTotalPrice("U$S 980");
		bookingGenaration.setTotalPricexAdult("U$S 450");
		return bookingGenaration;
		//return Response.created(new URI("/bookings/"+booking)).build();
	}

	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/bookings/{booking}/addPassengers")
	public void addPassengers(@PathParam("booking") String tempBooking,List<Passenger> passengers) {
		//Booking booking = getBooking(tempBooking);
		//booking.seteTickets(eTickets);
		for (Passenger p:passengers){
			
			System.out.println(p.getLastName1());
		}
	}

	

	private Booking getBooking(String tempBooking) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/countries")
	public List<Country> getCountries() throws ObjectNotFoundException{
		return api.getCountries();
	}


	@GET
	@Path("/flights/{flight}/getSeats")
	public List<Seat> getSeats(@QueryParam("flight") String flight) throws ObjectNotFoundException{
		return api.getSeats(flight);

	}
	
	
	@POST
	@Path("/bookings/{booking}/{passenger}/addSeat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSeat(@PathParam("booking") String tempBooking,@PathParam("passenger") String passenger,String seatNumber) {
		System.out.println("passenger "+passenger);
		System.out.println("booking "+tempBooking);
		System.out.println("seatNumber "+seatNumber);
	}


	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/bookings/{booking}/pay")
	public void makePayment(@PathParam("booking") String bookingCode,Payment payment) throws OperationException {
		api.makePayment(payment);
	}


	
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountInformation getAccountInformation(@HeaderParam("Authorization") String authorization) throws NotAuthorizedException{
		AccountInformation accountInfo = api.getAccountInformation(authorization);
		return accountInfo;

	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/bookings/{booking}/{lastName}/checkin")
	public BoardingPass confirmCheckIn(@PathParam("booking") String code,@PathParam("lastName") String lastName) throws ObjectNotFoundException {
		CheckInInformation checkInInformation=null;
		return api.confirmCheckIn(code,lastName,checkInInformation);
		
	}
	
	




	
	
	
	
	

}
