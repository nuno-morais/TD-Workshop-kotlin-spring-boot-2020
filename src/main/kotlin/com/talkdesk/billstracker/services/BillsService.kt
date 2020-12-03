package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.repositories.BillsRepository
import com.talkdesk.billstracker.exceptions.NotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository
) {
	fun findById(id: Int): Bill {
		val billToGet = billsRepository.findById(id)

		return if (billToGet.isPresent) billToGet.get()
			else throw NotFoundException()
	}

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		findById(id)
		return billsRepository.deleteById(id)
	}

	fun create(bill: Bill): Bill = billsRepository.save(bill)

	fun update(bill: Bill): Bill {
		findById(bill.id!!)
		return billsRepository.save(bill)
	}
}