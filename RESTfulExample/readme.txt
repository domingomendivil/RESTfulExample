This application was built using the following software:

- JDK 1.6
- Maven please download and install maven from https://maven.apache.org
- Tomcat 6 and compatible Servlet 2.5 Container http://tomcat.apache.org/
- MySQL5.7 download and install from https://www.mysql.com/




After installing this software, you should uncompress the zip files, and run

"mvn install" from the directory

Dependencies:

- Junit 
- Google API SDK
- Jersey 2.0 for JAX-RS 2.0 Implementation

This includes the script for generating the database tables.

Assumptions:

- The Air Ticket System is supposed to be integrated with:

	- A Payment Gateway, which allows the System to make payments with credit cards. The corresponding com.airport.payments.PaymentAPI interface and com.airport.payments.PaymentAPIImpl implementation classes, 
	  are provided to implement this future integration. The gateway will presumably be a REST or SOAP service provided.
	  
	- A Reservation Gateway, which allows the System to search for flights, make bookings, etc.The corresponding com.airport.reservations.ReservationAPI and com.airport.ReservationAPIImpl implementation classes,
	  are provided to implement this future integration as well, and will also presumabli a REST or SOAP web service. In order to make some full chain operations, such as booking searches, and
	  flight searches, the implementation class, gets this data from tables on the local database. This tables provides 
	  information of flights, bookings, etc.
	  
    - An Email Server with reliable messaging capability. An example of that service could be SES (Simple Email Service) provided by Amazon Web Services.
    
 
- In a production environment, The Air Ticket System must operate under HTTPS (TLS protocol) which enables a ciphered channel between the HTTP Rest Service.
  In order to do that is necessary to install the application under a digitally signed certificate X509 v3, emited by a trusted Certification Authority
  such as VeriSign or GoDaddy, which public root certificates are installed in current browsers, such as Firefox or Chrome.
  
- The ideal REST authentication scheme, should be implemented with an OAuth/OAuth2 with OpenId provider (OpenId connector), with JWT (JSON Web Tokens). 
  For time reasons, the current scheme is based on a Basic Authentication scheme. Social Login scheme, uses the obtained access tokens
  from the corresponding sdk libraries (from Google, Linkedin or Twitter).
	  
- The whole system information has been stored in MySQL relational database, in normalized tables. This does not necessary
  mean that this is the ideal way of storing each data. It will depend on many factors, such as operations frequency and system
  availability if this is the best scheme. Perhaps, some noSQL database is the best solution for some data.
  
- Some of these tables were created assuming that information is locally available to the Air Ticket System, but surely and in the real scenario
  this will not be the case, e.g. flights information. As a result, these tables will not be part of the final solution in a real 
  scenario.
  
  
  
  BUILDING THE APPLICATION
  
  In order to build and run the application, you must:
  1) uncompress the AirTickets folder in a local directory
  2) run "mvn install" from the uncompress AirTickets folder
  3) As a result, the subdirectory /target will be created, containing the AirTicket.war
  
  RUNNING THE APPLICATION
  1) The WAR could be deployed in a compatible Servlet 2.5 container, such as Tomcat 7.
  2) To install the WAR just copy it under the /webapps subdirectory of the Tomcat 7 home directory.
  3) To invoke the application, first start Tomcat Servlet Container
  4) Open a web browser and invoke http:[
   
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
-  
	  
	 
	  
	  




  








 

