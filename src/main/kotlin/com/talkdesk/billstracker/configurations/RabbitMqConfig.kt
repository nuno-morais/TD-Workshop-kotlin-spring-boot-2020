package com.talkdesk.billstracker.configurations

import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMqConfig(private val rabbitMqProperties: RabbitMqProperties)
{


    @Bean
    fun factory() = ConnectionFactory().apply {
        username = rabbitMqProperties.username
        password = rabbitMqProperties.password
        host = rabbitMqProperties.host
        port = rabbitMqProperties.port
    }

    @Bean
    fun connection(connectionFactory: ConnectionFactory) = connectionFactory.newConnection()

    @Bean
    fun channel(connection: Connection) = connection.createChannel()

    @Bean
    fun messageConverter() = Jackson2JsonMessageConverter()

}