package com.ss.scrumptious_notifcation.controller;

import javax.validation.Valid;

import com.ss.scrumptious_notifcation.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ss.scrumptious_notifcation.dto.OrderMessageDto;
import com.ss.scrumptious_notifcation.service.SimpleNotificationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationController {

	private final SimpleNotificationService notificationService;
	private final EmailService emailService;

	@PutMapping("/order-confirmation")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'OWNER')")
	public ResponseEntity<Void> sendOrderConfirmationText(@Valid @RequestBody OrderMessageDto orderMessageDto){
		notificationService.sendOrderConfirmationSMS(orderMessageDto);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/order-confirmation/email")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER', 'OWNER')")
	public ResponseEntity<Void> sendOrderConfirmationEmail(@Valid @RequestBody OrderMessageDto orderMessageDto) {
		emailService.sendOrderConfirmationEmail(orderMessageDto);
		return ResponseEntity.noContent().build();
	}
}
