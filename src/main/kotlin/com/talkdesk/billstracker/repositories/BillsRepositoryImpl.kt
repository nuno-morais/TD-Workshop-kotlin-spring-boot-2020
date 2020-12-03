package com.talkdesk.billstracker.repositories

import com.talkdesk.billstracker.entities.Bill
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger



/*
@Repository
class BillsRepositoryImpl : BillsRepository {
	private val index: AtomicInteger = AtomicInteger(0)
	private val storage = ConcurrentHashMap<Int, Bill>()

	override fun findById(id: Int) =
		Optional.ofNullable(storage[id])

	override fun findAll() = storage.values.toList()

	override fun deleteById(id: Int) {
		storage.remove(id)
	}

	override fun save(bill: Bill) =
		if (bill.id == null) {
			index.incrementAndGet().let { value ->
				storage.compute(value) { _, _ -> bill.copy(id = value) }!!
			}
		} else {
			storage.compute(bill.id) { _, _ -> bill }!!
		}

	override fun deleteAll() {
		index.set(0)
		storage.clear()
	}
}

 */