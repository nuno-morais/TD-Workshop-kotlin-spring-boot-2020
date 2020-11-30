package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.repositories.BillsRepository

class BillsService(
	private val billsRepository: BillsRepository
) {
	fun findById(id: Int): Bill = TODO()

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		TODO()
	}

	fun create(bill: Bill): Bill = TODO()

	fun update(bill: Bill): Bill = TODO()
}