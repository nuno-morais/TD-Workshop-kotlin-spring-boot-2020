package com.talkdesk.billstracker.services

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.repositories.BillsRepository
import org.springframework.stereotype.Service

@Service
class BillsService(
        private val BillsRepositoryImpl: BillsRepository
) {
    fun findById(id: Int): Bill = BillsRepositoryImpl.findById(id).orElseThrow {
        NotFoundException()
    }

    fun findAll() = BillsRepositoryImpl.findAll()

    fun delete(id: Int) {
        findById(id).run { BillsRepositoryImpl.deleteById(id) }

    }

    fun create(bill: Bill): Bill = BillsRepositoryImpl.save(bill)

    fun update(bill: Bill): Bill = findById(bill.id!!).run { BillsRepositoryImpl.save(bill) }
}