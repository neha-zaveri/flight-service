package com.paathshala.flight.flightservice.repository;

import com.paathshala.flight.flightservice.model.Flight;
import org.socialsignin.spring.data.dynamodb.repository.DynamoDBCrudRepository;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;

@EnableScan
public interface FlightRepository extends DynamoDBCrudRepository<Flight, String> {
}
