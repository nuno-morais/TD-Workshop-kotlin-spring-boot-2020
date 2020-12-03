package com.talkdesk.billstracker.repositories

import com.talkdesk.billstracker.entities.Bill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
@Repository
interface BillsRepository: JpaRepository<Bill,Int> {
	//fun findById(id: Int): Optional<Bill>
	//fun findAll(): List<Bill>
	//fun deleteById(id: Int)
	//fun save(bill: Bill): Bill
	//fun deleteAll()
}