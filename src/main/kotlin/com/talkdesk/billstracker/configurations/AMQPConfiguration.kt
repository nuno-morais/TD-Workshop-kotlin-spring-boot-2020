package com.talkdesk.billstracker.configurations

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AMQPConfiguration(private val properties: RabbitMQProperties) {

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val factory = ConnectionFactory()
        factory.host = properties.host
        factory.port = properties.port
        factory.username = properties.username
        factory.password = properties.password
        return factory
    }

    @Bean
    fun connection(connectionFactory: ConnectionFactory): Connection = connectionFactory.newConnection()

    @Bean
    fun channel(connection: Connection): Channel = connection.createChannel()
}
