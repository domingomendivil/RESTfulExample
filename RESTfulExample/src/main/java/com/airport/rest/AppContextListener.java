package com.airport.rest;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.airport.domain.Flight;

public class AppContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Initializing timer");
		TimerTask vodTimer = new VodTimerTask();

		Timer timer = new Timer();
		timer.schedule(vodTimer, 1000, (2 * 1000));

	}
	
	class VodTimerTask extends TimerTask {

		@Override
		public void run() {
			System.out.println("TimerTask " + new Date().toString());
		}
	}
	
	public void sendFlightCronogram(Flight flight){
		
	}
	

}
