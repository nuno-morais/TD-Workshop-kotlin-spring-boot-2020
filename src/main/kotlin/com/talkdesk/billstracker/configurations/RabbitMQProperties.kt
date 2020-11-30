package com.talkdesk.billstracker.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
class RabbitMQProperties {
	var queueName: String = "stats"
	var username: String = "test"
	var password: String = "test"
	var host: String = "localhost"
	var port: Int = 5672
}

// questions/26811924/spring-amqp-rabbitmq-3-3-5-access-refused-login-was-refused-using-authentica