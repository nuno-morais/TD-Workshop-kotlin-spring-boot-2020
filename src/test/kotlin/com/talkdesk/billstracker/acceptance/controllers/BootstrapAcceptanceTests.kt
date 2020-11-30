package com.talkdesk.billstracker.acceptance.controllers

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.repositories.BillsRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired

open class BootstrapAcceptanceTests {
	@Autowired
	private lateinit var billsRepository: BillsRepository

	private val _bills = listOf(
		Bill(1, "First Bill", 10, "acc-1"),
		Bill(2, "Second Bill", 20, "acc-1"),
		Bill(3, "Third Bill", 30, "acc-1"),
		Bill(4, "Fourth Bill", 40, "acc-1"),
		Bill(5, "Fifth Bill", 50, "acc-1"),
	)

	protected val bills = mutableListOf<Bill>()

	@AfterEach
	fun clear() {
		billsRepository.deleteAll()
	}

	@BeforeEach
	fun setup() {
		_bills.forEach {
			bills.add(
				billsRepository.save(it)
			)
		}
	}
}