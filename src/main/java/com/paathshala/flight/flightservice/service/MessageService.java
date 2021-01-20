package com.paathshala.flight.flightservice.service;

import com.paathshala.flight.flightservice.model.Flight;
import com.paathshala.flight.flightservice.model.Message;
import com.paathshala.flight.flightservice.model.MessageWithHostMetaData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
public class MessageService {
    private RestTemplate restTemplate;
    @Value("${message.service.url}")
    private String messageServiceUrl;
    @Value("${email}")
    private String email;

    @PostConstruct
    public void init() {
        restTemplate = new RestTemplate();
    }

    public MessageWithHostMetaData notify(Flight request) {
        Message message = new Message(request, email);
        HttpEntity<MessageWithHostMetaData> entity = new HttpEntity<>(getHttpHeaders());
        ResponseEntity<MessageWithHostMetaData> response = restTemplate.postForEntity(
                String.format("%s%s", messageServiceUrl, "/publish-message"), message, MessageWithHostMetaData.class);
        return response.getBody();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);
        headers.setAccept(acceptableMediaTypes);
        return headers;
    }

}
