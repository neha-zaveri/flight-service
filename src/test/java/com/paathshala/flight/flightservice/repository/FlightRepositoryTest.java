package com.paathshala.flight.flightservice.repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.paathshala.flight.flightservice.model.Flight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FlightRepositoryTest {
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    FlightRepository repository;

    @BeforeEach
    public void setup() {
        amazonDynamoDB.deleteTable("Flight");
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(Flight.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
    }

    @Test
    public void givenItemWithExpectedCost_whenRunFindAll_thenItemIsFound() {
        Flight flight = new Flight(123, "INDIGO", "AUS", "PNQ");
        repository.save(flight);
        List<Flight> result = (List<Flight>) repository.findAll();

        assertEquals(1, result.size());
        assertNotNull(result.get(0).getId());
        assertEquals("INDIGO",result.get(0).getAirlinesName());
        assertEquals("AUS",result.get(0).getDestinationName());
        assertEquals("PNQ",result.get(0).getBoardingName());
    }
}