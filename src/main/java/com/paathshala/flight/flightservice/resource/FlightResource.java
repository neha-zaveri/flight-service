package com.paathshala.flight.flightservice.resource;

import com.paathshala.flight.flightservice.model.BookingWithHostMetaData;
import com.paathshala.flight.flightservice.model.Flight;
import com.paathshala.flight.flightservice.model.FlightsWithHostMetaData;
import com.paathshala.flight.flightservice.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FlightResource {

	@Autowired
	private FlightService flightService;
	
	@GetMapping("/flights")
	public FlightsWithHostMetaData getAll(){
		return flightService.getAll();
	}
	
	@PostMapping("/book")
	public BookingWithHostMetaData bookFlight(@RequestBody Flight flight){
		return flightService.book(flight);
	}

	@PostMapping("/add")
	public Flight add(@RequestBody Flight flight){
		return flightService.add(flight);
	}
}
