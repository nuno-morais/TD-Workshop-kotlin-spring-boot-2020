package com.talkdesk.billstracker.controllers

import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.services.BillsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("bills")
class BillsController(
	private val billsService: BillsService
) {
	@GetMapping
	fun all() = billsService.findAll()

	@PostMapping
	fun create(@RequestBody bill: Bill): ResponseEntity<Bill> =
		ResponseEntity(billsService.create(bill), HttpStatus.CREATED)

	@GetMapping("/{id}")
	fun get(@PathVariable id: Int): ResponseEntity<Bill?> =
		ResponseEntity(billsService.findById(id), HttpStatus.OK)

	@PutMapping("/{id}")
	fun update(@RequestBody bill: Bill, @PathVariable id: Int): ResponseEntity<Bill> =
		ResponseEntity(billsService.update(bill.copy(id = id)), HttpStatus.OK)

	@DeleteMapping("/{id}")
	fun delete(@PathVariable id: Int): ResponseEntity<Any> =
		billsService.delete(id).run {
			ResponseEntity(HttpStatus.NO_CONTENT)
		}
}