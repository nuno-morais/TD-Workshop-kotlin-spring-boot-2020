package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
        private val Bananas: BillsRepository
) {
    fun findById(id: Int): Bill = Bananas.findById(id).orElseThrow {
        NotFoundException()
    }

    fun findAll() = Bananas.findAll()

    fun delete(id: Int) {
        findById(id).run { Bananas.deleteById(id) }

    }

    fun create(bill: Bill): Bill = Bananas.save(bill)

    fun update(bill: Bill): Bill = findById(bill.id!!).run { Bananas.save(bill) }
}