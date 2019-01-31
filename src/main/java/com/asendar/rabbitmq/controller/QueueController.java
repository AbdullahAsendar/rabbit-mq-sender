package com.asendar.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asendar.rabbitmq.core.QueueService;

/**
 * @author asendar
 *
 */
@RestController
@RequestMapping("/queue")
public class QueueController {

	@Autowired
	private QueueService queueService;

	@GetMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestParam String queue, @RequestParam String message) {

		boolean success = queueService.sendMessage(queue, message);

		if (!success)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return ResponseEntity.ok().build();
	}

	@GetMapping("/create")
	public ResponseEntity<String> sendMessage(@RequestParam String queue, @RequestParam boolean durable) {

		boolean success = queueService.createQueue(queue, durable);

		if (!success)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return ResponseEntity.ok().build();
	}

}
