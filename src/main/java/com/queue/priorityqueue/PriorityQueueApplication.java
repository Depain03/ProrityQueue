package com.queue.priorityqueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriorityQueueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriorityQueueApplication.class, args);
	}
}
