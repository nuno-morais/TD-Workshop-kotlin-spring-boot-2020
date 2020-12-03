package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository
) {
	fun findById(id: Int): Bill = billsRepository.findById(id).orElseThrow{ NotFoundException() }

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		this.findById(id) // will throw NotFoundException if bill doesn't exist
		billsRepository.deleteById(id)
	}

	fun create(bill: Bill): Bill = billsRepository.save(bill)

	fun update(bill: Bill): Bill {
		if (bill.id == null) {
			throw NotFoundException()
		}
		this.findById(bill.id) // will throw NotFoundException if bill doesn't exist
		return billsRepository.save(bill)
	}
}