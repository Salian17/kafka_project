package com.example.secondService;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@RequiredArgsConstructor
public class SecondServiceApplication {

	private final JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(SecondServiceApplication.class, args);
	}

	@KafkaListener(topics = "alive_topic", groupId = "group_id")
	public void listen(String message) {
		jdbcTemplate.update("INSERT INTO service_status (message) VALUES (?)", message);
		System.out.println("Received and saved message: " + message);
	}
}