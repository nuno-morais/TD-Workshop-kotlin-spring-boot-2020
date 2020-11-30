package com.talkdesk.billstracker.configurations

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AMQPConfig(
	private val rabbitMQProperties: RabbitMQProperties
) {
	@Bean
	fun messageConverter() = Jackson2JsonMessageConverter()

	@Bean
	fun connectionFactory(): ConnectionFactory {
		val factory = ConnectionFactory()
		factory.username = rabbitMQProperties.username
		factory.password = rabbitMQProperties.password
		factory.host = rabbitMQProperties.host
		factory.port = rabbitMQProperties.port
		return factory
	}

	@Bean
	fun connection(connectionFactory: ConnectionFactory) = connectionFactory.newConnection()

	@Bean
	fun channel(connection: Connection) = connection.createChannel()
}