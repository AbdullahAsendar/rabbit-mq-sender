package com.asendar.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asendar.rabbitmq.core.QueueService;
import com.rabbitmq.client.BuiltinExchangeType;

/**
 * @author asendar
 *
 */
@RestController
@RequestMapping("/exchange")
public class ExchangeController {

	@Autowired
	private QueueService queueService;

	@GetMapping("/send")
	public ResponseEntity<String> sendMessage(@RequestParam String exchangeName, @RequestParam String message) {

		boolean success = queueService.exchange(exchangeName, message);

		if (!success)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return ResponseEntity.ok().build();
	}

	@GetMapping("/create")
	public ResponseEntity<String> sendMessage(@RequestParam String exchange, @RequestParam BuiltinExchangeType type) {

		boolean success = queueService.createExchange(exchange, type);

		if (!success)
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		return ResponseEntity.ok().build();
	}

}
