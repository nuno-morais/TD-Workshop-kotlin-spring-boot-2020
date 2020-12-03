package com.talkdesk.billstracker.services

import com.rabbitmq.client.ConnectionFactory
import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.models.StatsEvent
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service

@Service
class BillsService(private val billsRepository: BillsRepository, val rabbitMqSender: RabbitMqSender) {


	fun findById(id: Int): Bill = billsRepository.findById(id).let {
		if (it.isPresent) {

			it.get()
		}
		else
			throw NotFoundException()
	}

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		billsRepository.findById(id).let {
			if(it.isPresent) {
				billsRepository.deleteById(id)
				rabbitMqSender.send(StatsEvent("deleted", it.get().accountId))
			}
			else
				throw NotFoundException()
		}
	}

	fun create(bill: Bill): Bill = billsRepository.save(bill).also {
		rabbitMqSender.send(StatsEvent("created", bill.accountId))

	}

	fun update(bill: Bill): Bill  {

		bill?.id?.let {
			billsRepository.findById(it).let {
				if (it.isPresent) {
					rabbitMqSender.send(StatsEvent("updated", bill.accountId))
					return billsRepository.save(bill)
				}
				else
					throw NotFoundException()
			}
		}?:throw NotFoundException()
	}
}