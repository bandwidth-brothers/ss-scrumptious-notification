package com.ss.scrumptious_notifcation.service;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.amazonaws.services.sns.model.SubscribeResult;
import com.ss.scrumptious_notifcation.dto.OrderMessageDto;

@Service
public class SimpleNotificationService {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleNotificationService.class);


	@Autowired
	private AmazonSNS awsSNS;

	// Your topic name.
    @Value("${amazon.sns.topic.name}")
    private String topicName;

	protected AmazonSNS getClient() {
		return awsSNS;
	}

	public void subscribeToSNSTopic(String phoneNumber) {
		SubscribeResult request = awsSNS.subscribe(new SubscribeRequest()
				.withTopicArn(topicName)
				.withProtocol("sms")
				.withEndpoint("+1" + phoneNumber));

		LOG.info("SNS Number successfully subscribed:: " + request.getSubscriptionArn());
	}

	public void publishToTopic(String message) {
		PublishResult result = awsSNS.publish(new PublishRequest()
				.withTopicArn(topicName)
				.withMessage(message));

		LOG.info("Message sent successfully to Topic:: " + result.getMessageId());
	}

	public void publishSingleSMS(String message, String phoneNumber) {
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<>();
		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("Scrumptious")
				.withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional")
				.withDataType("String"));

		PublishResult result = awsSNS.publish(new PublishRequest()
				.withMessage(message)
				.withPhoneNumber("+1" + phoneNumber)
				.withMessageAttributes(smsAttributes));

		LOG.info("Message sent successfully:: " + result.getMessageId());
	}

	public void sendOrderConfirmationSMS(OrderMessageDto messageDto) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
		String formattedDate = messageDto.getRequestedDeliveryTime().format(formatter);

		String message = "Hey " + messageDto.getCustomerName() + "! I hope you're hungry! Your order #" + messageDto.getConfirmationCode()
		+ " from " + messageDto.getRestaurantName() + " at " + messageDto.getRestaurantAddress() + " has been received and "
			+ "has an approximate delivery time of " + formattedDate;
		publishSingleSMS(message, messageDto.getCustomerPhoneNumber());
	}


}
