package com.talkdesk.billstracker.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.talkdesk.billstracker.configurations.RabbitMQProperties
import com.talkdesk.billstracker.entities.BillStatsEvent
import com.talkdesk.billstracker.entities.BillStatsOperation
import org.springframework.stereotype.Service

@Service
class BillsPublisher(
        private val objectMapper: ObjectMapper,
        private val channel: Channel,
        private val rabbitMQProperties: RabbitMQProperties
) {

    fun publishCreateEvent(accountId: String) {
        val event = BillStatsEvent(BillStatsOperation.CREATED, accountId)
        publish(event)
    }

    fun publishUpdateEvent(accountId: String) {
        val event = BillStatsEvent(BillStatsOperation.UPDATED, accountId)
        publish(event)
    }

    fun publishDeleteEvent(accountId: String) {
        val event = BillStatsEvent(BillStatsOperation.DELETED, accountId)
        publish(event)
    }

    private fun publish(event: BillStatsEvent) {
        val message = objectMapper.writeValueAsString(event)
        channel.basicPublish("", rabbitMQProperties.queueName, null, message.toByteArray(Charsets.UTF_8))
    }
}
