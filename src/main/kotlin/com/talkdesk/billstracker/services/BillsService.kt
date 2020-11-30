package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.gateways.BillsPublisher
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository,
	private val billsPublisher: BillsPublisher
) {
	fun findById(id: Int): Bill =
		billsRepository.findById(id).let {
			if (it.isPresent) {
				it.get()
			} else throw NotFoundException()
		}

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		findById(id).let { bill ->
			billsRepository.deleteById(bill.id!!)
			billsPublisher.publishDeleteOption(bill.accountId)
		}
	}

	fun create(bill: Bill) =
		billsRepository.save(bill).also {
			billsPublisher.publishCreateOption(bill.accountId)
		}

	fun update(bill: Bill) =
		findById(bill.id!!).run {
			billsRepository.save(bill).also {
				billsPublisher.publishUpdateOption(bill.accountId)
			}
		}
}