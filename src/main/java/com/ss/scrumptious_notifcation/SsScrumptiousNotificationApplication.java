package com.ss.scrumptious_notifcation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SsScrumptiousNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsScrumptiousNotificationApplication.class, args);
	}

}
