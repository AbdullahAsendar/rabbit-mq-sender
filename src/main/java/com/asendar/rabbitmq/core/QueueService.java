package com.asendar.rabbitmq.core;

import com.rabbitmq.client.BuiltinExchangeType;

/**
 * @author asendar
 *
 */
public interface QueueService {

	boolean sendMessage(String queueName, String message);

	boolean createQueue(String queue, boolean durable);

	boolean createExchange(String exchangeName, BuiltinExchangeType type);

	boolean exchange(String exchangeName, String message);

}
