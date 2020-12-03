package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.gateway.Gateway
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository,
	private val gateway: Gateway
) {
	fun findById(id: Int): Bill = billsRepository.findById(id).orElseThrow { NotFoundException() }

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) = findById(id).run { billsRepository.deleteById(id).also { gateway.publishDeleteOption(this.accountId) } }

	fun create(bill: Bill): Bill = billsRepository.save(bill).also { gateway.publishCreateOption(bill.accountId)  }

	fun update(bill: Bill): Bill = bill.id?.let { findById(it).run { billsRepository.save(bill).also { gateway.publishUpdateOption(accountId) } } }
				?: throw NotFoundException()
}