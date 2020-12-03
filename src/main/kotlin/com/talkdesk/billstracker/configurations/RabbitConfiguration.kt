package com.talkdesk.billstracker.configurations

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfiguration (private val rabbitProperties: RabbitProperties){
    @Bean
    fun rabbitFactory() = ConnectionFactory().apply {
        username = rabbitProperties.username
        password = rabbitProperties.password
        port = rabbitProperties.port
        host = rabbitProperties.host
    }
    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun rabbitConnection(connectionFactory: ConnectionFactory) = connectionFactory.newConnection()

    @Bean
    fun channel(connection: Connection) = connection.createChannel()
}
@Configuration
@ConfigurationProperties(prefix="rabbitmq")
class RabbitProperties{
    var username :String = "test"
    var password :String = "test"
    var host :String = "localhost"
    var port :Int = 5673
    var queueName :String = "stats"
}