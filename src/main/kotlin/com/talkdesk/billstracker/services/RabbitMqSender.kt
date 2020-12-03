package com.talkdesk.billstracker.services;
import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.talkdesk.billstracker.configurations.RabbitMqProperties
import com.talkdesk.billstracker.models.StatsEvent
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service

@Service
class RabbitMqSender(private val channel: Channel, private val rabbitMqProperties: RabbitMqProperties,private val objectMapper: ObjectMapper) {
    fun send(message : StatsEvent) {
        val sendMessage = objectMapper.writeValueAsString(message)
        channel.basicPublish("",rabbitMqProperties.queueName,null,sendMessage.toByteArray())
        println("[x] Sent '$message'")
    }
}
