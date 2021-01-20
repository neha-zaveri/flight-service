package com.paathshala.flight.flightservice.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.paathshala.flight.flightservice.repository.FlightRepository;
import org.apache.logging.log4j.util.Strings;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableDynamoDBRepositories(basePackageClasses = FlightRepository.class)
public class DynamoDBConfig {
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;

    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;

    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        if (Strings.isNotEmpty(amazonDynamoDBEndpoint)) {
            return AmazonDynamoDBClientBuilder.standard()
                    .withCredentials(amazonAWSCredentials())
                    .withEndpointConfiguration(new AwsClientBuilder
                            .EndpointConfiguration(amazonDynamoDBEndpoint, Regions.US_EAST_2.getName()))
                    .build();
        }
        return AmazonDynamoDBClientBuilder.standard()
                .withRegion(Regions.US_EAST_2).build();
    }

    @Bean
    public AWSStaticCredentialsProvider amazonAWSCredentials() {
        AWSCredentials basicAWSCredentials = new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
        return new AWSStaticCredentialsProvider(basicAWSCredentials);
    }
}
