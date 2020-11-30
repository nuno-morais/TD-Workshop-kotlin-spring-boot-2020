package com.talkdesk.billstracker.controllers

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.services.BillsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("bills")
class BillsController(
	private val billsService: BillsService
) {
	@GetMapping
	fun all() = billsService.findAll()

	fun create(bill: Bill): ResponseEntity<Bill> =
		ResponseEntity(billsService.create(bill), HttpStatus.CREATED)

	fun get(id: Int): ResponseEntity<Bill?> =
		ResponseEntity(billsService.findById(id), HttpStatus.OK)

	fun update(bill: Bill, id: Int): ResponseEntity<Bill> =
		ResponseEntity(billsService.update(bill.copy(id = id)), HttpStatus.OK)

	fun delete(id: Int): ResponseEntity<Any> =
		billsService.delete(id).run {
			ResponseEntity(HttpStatus.NO_CONTENT)
		}
}