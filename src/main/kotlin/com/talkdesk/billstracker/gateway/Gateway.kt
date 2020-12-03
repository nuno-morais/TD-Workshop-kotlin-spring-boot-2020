package com.talkdesk.billstracker.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.talkdesk.billstracker.configurations.RabbitProperties
import com.talkdesk.billstracker.entities.StatsEvent
import com.talkdesk.billstracker.entities.StatsOperation
import org.springframework.stereotype.Service

@Service
class Gateway(
        private val objectMapper : ObjectMapper,
        private val channel: Channel,
        private val rabbitMQProperties: RabbitProperties
) {
    fun publishCreateOption(accountId: String) {
        val event = StatsEvent(StatsOperation.CREATED, accountId)
        send(event)
    }

    fun publishUpdateOption(accountId: String) {
        val event = StatsEvent(StatsOperation.UPDATED, accountId)
        send(event)
    }

    fun publishDeleteOption(accountId: String) {
        val event = StatsEvent(StatsOperation.Deleted, accountId)
        send(event)
    }

    private fun send(event: StatsEvent) {
        val message = objectMapper.writeValueAsString(event)
        channel.basicPublish("", rabbitMQProperties.queueName, null, message.toByteArray(Charsets.UTF_8))
    }
}