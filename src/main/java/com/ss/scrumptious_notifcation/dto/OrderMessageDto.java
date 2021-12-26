package com.ss.scrumptious_notifcation.dto;

import java.time.ZonedDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMessageDto {

	private String customerName;
	private String customerPhoneNumber;
	private String restaurantName;
	private String restaurantAddress;
	
	private String confirmationCode;
	private String preparationStatus;
	private ZonedDateTime requestedDeliveryTime;
}
