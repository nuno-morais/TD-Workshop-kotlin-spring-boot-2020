package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BillsService(
	private val billsRepository: BillsRepository
) {
	fun findById(id: Int): Bill = billsRepository.findById(id).let {
		if (it.isPresent)
			it.get()
		else
			throw NotFoundException()
	}

	fun findAll() = billsRepository.findAll()

	fun delete(id: Int) {
		billsRepository.findById(id).let {
			if(it.isPresent)
				billsRepository.deleteById(id)
			else
				throw NotFoundException()
		}
	}

	fun create(bill: Bill): Bill = billsRepository.save(bill)

	fun update(bill: Bill): Bill  {

		bill?.id?.let {
			billsRepository.findById(it).let {
				if (it.isPresent)
					return billsRepository.save(bill)
				else
					throw NotFoundException()
			}
		}?:throw NotFoundException()
	}
}