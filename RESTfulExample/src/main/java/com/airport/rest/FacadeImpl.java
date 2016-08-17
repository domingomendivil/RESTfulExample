package com.airport.rest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.airport.dao.DAO;
import com.airport.dao.DAOImpl;
import com.airport.domain.AccountInformation;
import com.airport.domain.Airport;
import com.airport.domain.AirportList;
import com.airport.domain.AvailableFlights;
import com.airport.domain.BoardingPass;
import com.airport.domain.Booking;
import com.airport.domain.BookingGeneration;
import com.airport.domain.Country;
import com.airport.domain.CreditCard;
import com.airport.domain.ETicket;
import com.airport.domain.ETicketStatus;
import com.airport.domain.Flight;
import com.airport.domain.FlightLine;
import com.airport.domain.FlightPrice;
import com.airport.domain.FlightSearch;
import com.airport.domain.FlightSummary;
import com.airport.domain.Passenger;
import com.airport.domain.Seat;
import com.airport.domain.Status;
import com.airport.domain.Token;
import com.airport.domain.TwitterToken;
import com.airport.domain.User;
import com.airport.email.EMail;
import com.airport.email.EMailImpl;
import com.airport.factory.Factory;
import com.airport.security.NotAuthorizedException;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;


@Path("/api")
public class FacadeImpl  {
	
	private DAO dao = new DAOImpl();
	private EMail email = new EMailImpl();
	
	
	private API api = new Factory().getAPI();
	
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/airports")
	public AirportList getAirports(@QueryParam("letters") String letters){
		List<Airport> airports;
		if (letters==null){
			airports= (List<Airport>)dao.getAll(Airport.class);
		}else{
			airports= (List<Airport>)dao.getByCriteria(Airport.class," e.iata like ?1",letters);
		}
		AirportList list = new AirportList();
		list.setAirports(airports);
		return list;
	}
	

	
	

	@GET
	@Path("/bookings/{code}")
	public Booking getBooking(@PathParam("code") String code,@QueryParam("lastName") String lastName){
		System.out.println("code: "+code);
		System.out.println("lastName: "+lastName);
		Booking booking = (Booking)dao.getbyId(Booking.class,code);
		if (verifyLastName(booking, lastName)){
			return booking;	
		}{
			return null;
		}
	}
	
	

	@POST
	@Path("/bookings/{code}/cancel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean cancelBooking(@PathParam("code")String code, String lastName) throws FacadeImplException {
		Booking booking = getBooking(code);
		if (verifyLastName(booking,lastName)){
			dao.begin();
			booking.setStatus(Status.cancelled);
			dao.commit();
			try {
				sendEmailCancelledBooking(booking);
			} catch (IOException e) {
				throw new FacadeImplException(e);
			}
			return true;
		}else{
			return false;
		}
	}


	private String readFile(String file) throws IOException {
	    BufferedReader reader = new BufferedReader(new FileReader (file));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	    try {
	        while((line = reader.readLine()) != null) {
	            stringBuilder.append(line);
	            stringBuilder.append(ls);
	        }

	        return stringBuilder.toString();
	    } finally {
	        reader.close();
	    }
	}
	
	
	private void sendEmailCancelledBooking(Booking booking) throws IOException {
		String file = readFile("canceEmail");
		file.replaceAll("<%=booking.code%>",booking.getCode());
		email.sendEmail(file);
	}


	private boolean verifyLastName(Booking booking,String lastName) {
		for (ETicket eTicket:booking.geteTickets()){
			if ((eTicket.getPassenger().getLastName1()+" "+eTicket.getPassenger().getLastName2()).equals(lastName)){
				return true;
			}
		}
		return false;
	}


	@GET
	@Path("/flights")
	public AvailableFlights getFlights(@QueryParam("from") String fromLocation, @QueryParam("to") String toLocation,  @QueryParam("fromDate") String fromDate,  @QueryParam("toDate") String toDate) {
		@SuppressWarnings("unchecked")
		List<Flight> flights = (List<Flight>)dao.getAll(Flight.class);
		AvailableFlights avFlights = new AvailableFlights();
		avFlights.setDepartureFlights(flights);
		avFlights.setReturnFlights(flights);
		return avFlights;
	}
	
	
	@GET
	@Path("/flights/prices")
	public FlightSummary getFlightsPrices(@QueryParam("from") String from,@QueryParam("to") String to) {
		FlightSummary flightSum = new FlightSummary();
		List<String> dates = new ArrayList<String>();
		
		
		dates.add("SUN 08/07/2016");
		dates.add("MON 08/08/2016");
		dates.add("TUE 08/09/2016");
		dates.add("WED 08/10/2016");
		flightSum.setDates(dates);
		List<FlightLine> flightLines = new ArrayList<FlightLine>();
		FlightLine line = new FlightLine();
		line.setFrom("SUN 08/07/2016");
		List<FlightPrice> flightPrices = new ArrayList<FlightPrice>();
		FlightPrice price = new FlightPrice();
		price.setPrice("U$S90");
		price.setTo(new Date());
		flightPrices.add(price);
		FlightPrice price2 = new FlightPrice();
		price2.setPrice("U$S180");
		price2.setTo(new Date());
		flightPrices.add(price2);
		FlightPrice price3 = new FlightPrice();
		price3.setPrice("U$S200");
		price3.setTo(new Date());
		flightPrices.add(price3);
		flightPrices.add(price3);
		line.setFlightPrices(flightPrices);
		flightLines.add(line);
		flightLines.add(line);
		flightLines.add(line);
		flightLines.add(line);
		flightSum.setFlightLines(flightLines);
		return flightSum;
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

	private Token generateToken(String userName) {
		// TODO Auto-generated method stub
		String str = UUID.randomUUID().toString();
		return new Token(str);
	}

	private User getUser(String user) {
		return (User)dao.getbyId(User.class, user);
	}

	private String encrypt(String password) {
		// TODO Auto-generated method stub
		return password;
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
	public List<Country> getCountries(){
		List<Country> list =(List<Country>)dao.getAll(Country.class);
		return list;
	}


	@GET
	@Path("/flights/{flight}/getSeats")
	public List<Seat> getSeats(@QueryParam("flight") String flight){
		ArrayList<Seat> list = new ArrayList<Seat>();
		Seat seat = new Seat();
		seat.setNumber("38A");
		seat.setAvailable(true);
		Seat seat2 = new Seat();
		seat.setNumber("38B");
		seat.setAvailable(true);
		list.add(seat2);
		return list;
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
	public void makePayment2(@PathParam("booking") String bookingCode,CreditCard creditCard) {
		// TODO Auto-generated method stub
		System.out.println("Making another Payment "+bookingCode);
		// TODO Auto-generated method stub
		System.out.println("Payment "+creditCard.getNumber());
	}


	
	@GET
	@Path("/profile")
	@Produces(MediaType.APPLICATION_JSON)
	public AccountInformation getAccountInformation(@HeaderParam("Authorization") String authorization){
		try {
			AccountInformation accountInfo = api.getAccountInformation(authorization);
			return accountInfo;
		} catch (NotAuthorizedException e) {
			return null;
		}
	}


	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/bookings/{booking}/{lastName}/checkin")
	public BoardingPass confirmCheckIn(@PathParam("booking") String code,@PathParam("lastName") String lastName) {
		Booking booking = getBooking(code);
		ETicket eTicket = getETicket(code, lastName);
		if (eTicket!=null){
			eTicket.setStatus(ETicketStatus.notCheckedIn);
			eTicket.setBooking(booking);
			BoardingPass boardingPass = new BoardingPass();
			boardingPass.seteTicket(eTicket);
			boardingPass.setBoardingTime(new Date());
			return boardingPass;
		}else{
			return null;
		}
		
	}
	
	
	private ETicket getETicket(String code, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}


	@GET
	@Path("/twittertoken")
	public TwitterToken getTwitterToken() throws TwitterException{
		TwitterToken token =new TwitterToken();
		token.setToken(getAccessToken().toString());
		return token;
	}
	
	private static AccessToken getAccessToken() throws TwitterException{
		Twitter twitter = new TwitterFactory().getInstance();
		System.setProperty("twitter4j.oauth.consumerKey","jiOMFZ9CDxEGqEYZCHv2hJGKs");
		System.setProperty("twitter4j.oauth.consumerSecret","R7zqjCDmbXPXlSTAy0A7SzXvAxMhOjY1jgw5rwAmYcJhU730JQ");
		String callbackURL ="http://localhost:63342/Airport_Crossover/index.html";
		System.out.println("antes de llamar");
		RequestToken requestToken =twitter.getOAuthRequestToken(callbackURL);
		System.out.println("REQ TOKEN "+requestToken.getToken());
		System.out.println("despues de llamar");
		String res = requestToken.getAuthenticationURL();
		System.out.println("RES: "+res);
		AccessToken accessToken = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    while (null == accessToken) {
	      System.out.println("Open the following URL and grant access to your account:");
	      System.out.println(requestToken.getAuthorizationURL());
	      System.out.print("Enter the PIN(if aviailable) or just hit enter.[PIN]:");
	      String pin;
		try {
			pin = br.readLine();
		      try{
		    	  System.out.println("PIN introducido es: "+pin);
			         if(pin.length() > 0){
			        	 System.out.println("es mayor a 0");
			           accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			         }else{
			           accessToken = twitter.getOAuthAccessToken();
			         }
			      } catch (TwitterException te) {
			        if(401 == te.getStatusCode()){
			          System.out.println("Unable to get the access token.");
			        }else{
			          te.printStackTrace();
			        }
			      }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    return accessToken;
	}
	
	
	
	

}
