package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.gateway.BillsPublisher
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository,
	private val publisher: BillsPublisher
) {
	fun findById(id: Int): Bill = billsRepository.findById(id).orElseThrow{ NotFoundException() }

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		this.findById(id).let { bill ->
			billsRepository.deleteById(bill.id!!)
			publisher.publishDeleteEvent(bill.accountId)
		}
	}

	fun create(bill: Bill): Bill = billsRepository.save(bill).also {
		publisher.publishCreateEvent(bill.accountId)
	}

	fun update(bill: Bill): Bill {
		if (bill.id == null) {
			throw NotFoundException()
		}

		return this.findById(bill.id).run {
			billsRepository.save(bill).also {
				publisher.publishUpdateEvent(bill.accountId)
			}
		}
	}
}