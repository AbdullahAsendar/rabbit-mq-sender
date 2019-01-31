package com.asendar.rabbitmq.core;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author asendar
 *
 */
@Service
public class RabbitMQService implements QueueService {

	@Value("${rabbitmq.host}")
	private String rabbitmqHost;
	
	@Value("${rabbitmq.prefetchCount}")
	private int prefetchCount;

	private ConnectionFactory connectionFactory;
	private Connection connection;

	@PostConstruct
	private void init() throws Exception {
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(rabbitmqHost);

		connection = connectionFactory.newConnection();
	}

	@PreDestroy
	private void tearDown() throws Exception {
		connection.close();
	}

	@Override
	public boolean sendMessage(String queueName, String message) {
		try (Channel channel = getChannel()) {
			channel.basicPublish("", queueName, null, message.getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean createQueue(String queueName, boolean durable) {
		try (Channel channel = getChannel()) {
			channel.queueDeclare(queueName, durable, false, false, null);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public boolean createExchange(String exchangeName, BuiltinExchangeType type) {
		try (Channel channel = getChannel()) {
			channel.exchangeDeclare(exchangeName, type);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	@Override
	public boolean exchange(String exchangeName, String message) {
		try (Channel channel = getChannel()) {
			channel.basicPublish(exchangeName, "", null, message.getBytes());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private Channel getChannel() throws IOException {
		Channel channel = connection.createChannel();
		channel.basicQos(prefetchCount);
		return channel;
	}

}
