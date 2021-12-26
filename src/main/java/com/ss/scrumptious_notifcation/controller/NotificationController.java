package com.ss.scrumptious_notifcation.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ss.scrumptious_notifcation.dto.OrderMessageDto;
import com.ss.scrumptious_notifcation.service.SimpleNotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {
	
	private final SimpleNotificationService notificationService;
	
	@PutMapping("/order-confirmation")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'OWNER')")
	public ResponseEntity<Void> sendOrderConfirmationText(@Valid @RequestBody OrderMessageDto orderMessageDto){
		notificationService.sendOrderConfirmationSMS(orderMessageDto);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/test")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'OWNER')")
	public String test() {
		return "TEST";
	}
}