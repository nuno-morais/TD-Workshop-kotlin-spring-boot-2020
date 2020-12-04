package com.talkdesk.billstracker.configurations

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
class RabbitMQProperties {
    val queueName: String = "stats"
    val host: String = "localhost"
    val port: Int = 5673
    val username: String = "test"
    var password: String = "test"
}
