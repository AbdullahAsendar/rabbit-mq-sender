package com.asendar.rabbitmq.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author asendar
 *
 */
@SpringBootApplication
@ComponentScan("com.asendar.rabbitmq")
public class Launcher {

	public static void main(String[] args) {
		SpringApplication.run(Launcher.class, args);
	}

}
