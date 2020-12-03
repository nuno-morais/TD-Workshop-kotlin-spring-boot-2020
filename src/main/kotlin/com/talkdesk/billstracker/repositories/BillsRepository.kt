package com.talkdesk.billstracker.repositories

import com.talkdesk.billstracker.entities.Bill
import org.springframework.stereotype.Repository
import java.util.Optional

interface BillsRepository {
	fun findById(id: Int): Optional<Bill>
	fun findAll(): List<Bill>
	fun deleteById(id: Int)
	fun save(bill: Bill): Bill
	fun deleteAll()
}