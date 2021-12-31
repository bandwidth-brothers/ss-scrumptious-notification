package com.ss.scrumptious_notifcation.service;


import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.SendTemplatedEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ss.scrumptious_notifcation.dto.OrderMessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

    private final AmazonSimpleEmailService client;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${amazon.ses.source.email}")
    private String sourceEmail;

    public void sendOrderConfirmationEmail(OrderMessageDto orderMessageDto){

        orderMessageDto.setDeliveryTime();
        Destination destination = new Destination();
        destination.setToAddresses(Arrays.asList(orderMessageDto.getCustomerEmail()));
        SendTemplatedEmailRequest templatedEmailRequest = new SendTemplatedEmailRequest();
        templatedEmailRequest.withDestination(destination);
        templatedEmailRequest.withTemplate("OrderDetailsTemplate");

        try {
            templatedEmailRequest.withTemplateData(objectMapper.writeValueAsString(orderMessageDto));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        templatedEmailRequest.withSource(sourceEmail);
        client.sendTemplatedEmail(templatedEmailRequest);
        log.info("email sent: " + orderMessageDto.getCustomerEmail());
    }
}
