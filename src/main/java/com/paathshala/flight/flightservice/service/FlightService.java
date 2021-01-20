package com.paathshala.flight.flightservice.service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.paathshala.flight.flightservice.model.BookingWithHostMetaData;
import com.paathshala.flight.flightservice.model.Flight;
import com.paathshala.flight.flightservice.model.FlightsWithHostMetaData;
import com.paathshala.flight.flightservice.model.Message;
import com.paathshala.flight.flightservice.repository.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FlightService {

    @Autowired
    private FlightRepository repository;

    @Autowired
    private Environment env;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    private MessageService messageService;

    @PostConstruct
    public void init() {
        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
        CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(Flight.class);
        tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
        TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightService.class);

    public FlightsWithHostMetaData getAll() {
        Iterable<Flight> flightsSaved = repository.findAll();
        List<Flight> flights = StreamSupport.stream(flightsSaved.spliterator(), true)
                .collect(Collectors.toList());
        logHostInfo();
        return FlightsWithHostMetaData.builder()
                .withFlights(flights)
                .withNameSpace(env.getProperty("MY_POD_NAMESPACE"))
                .withPodIp(env.getProperty("MY_POD_IP"))
                .withPodName(env.getProperty("MY_POD_NAME"))
                .build();
    }

    private void logHostInfo() {
        LOGGER.info(String.format("%s is running on this IP %s in the namespace %s", env.getProperty("MY_POD_NAME"),
                env.getProperty("MY_POD_IP"), env.getProperty("MY_POD_NAMESPACE")));
    }

    public BookingWithHostMetaData book(Flight flight) {
        messageService.notify(flight);
        logHostInfo();
        return BookingWithHostMetaData.builder()
                .withFlight(flight)
                .withMessage("SUCCESS")
                .withNameSpace(env.getProperty("MY_POD_NAMESPACE"))
                .withPodIp(env.getProperty("MY_POD_IP"))
                .withPodName(env.getProperty("MY_POD_NAME"))
                .build();
    }

    public Flight add(Flight flight) {
        return repository.save(flight);
    }
}
