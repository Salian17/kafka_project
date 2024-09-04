package com.example.firstService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FirstServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstServiceApplication.class, "--debug");
	}

	@Bean
	public CommandLineRunner sendMessage(KafkaTemplate<String, String> kafkaTemplate) {
		return args -> {
			while (true) {
				kafkaTemplate.send("alive_topic", "I'm alive");
				System.out.println("Sent: I'm alive");
				Thread.sleep(5000);
			}
		};
	}
}
