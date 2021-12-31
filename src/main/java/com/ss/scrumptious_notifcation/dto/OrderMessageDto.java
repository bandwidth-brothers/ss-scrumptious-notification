package com.ss.scrumptious_notifcation.dto;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
	private String customerEmail;
	private String customerPhoneNumber;
	private String restaurantName;
	private String restaurantAddress;

	private String confirmationCode;
	private String preparationStatus;
	private ZonedDateTime requestedDeliveryTime;
	private String deliveryTime;

	public void setDeliveryTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");

		this.deliveryTime = this.requestedDeliveryTime != null ? this.requestedDeliveryTime.format(formatter) : "2022-01-01 00:00 ";
	}
}
