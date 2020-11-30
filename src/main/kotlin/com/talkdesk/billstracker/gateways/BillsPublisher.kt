package com.talkdesk.billstracker.gateways

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.Channel
import com.talkdesk.billstracker.configurations.RabbitMQProperties
import com.talkdesk.billstracker.entities.BillStatsEvent
import com.talkdesk.billstracker.entities.StatsOperation
import org.springframework.stereotype.Service

@Service
class BillsPublisher(
	private val objectMapper: ObjectMapper,
	private val channel: Channel,
	private val rabbitMQProperties: RabbitMQProperties
) {
	fun publishCreateOption(accountId: String) {
		val event = BillStatsEvent(StatsOperation.CREATED, accountId)
		send(event)
	}

	fun publishUpdateOption(accountId: String) {
		val event = BillStatsEvent(StatsOperation.UPDATED, accountId)
		send(event)
	}

	fun publishDeleteOption(accountId: String) {
		val event = BillStatsEvent(StatsOperation.Deleted, accountId)
		send(event)
	}

	private fun send(event: BillStatsEvent) {
		val message = objectMapper.writeValueAsString(event)
		channel.basicPublish("", rabbitMQProperties.queueName, null, message.toByteArray(Charsets.UTF_8))
	}
}