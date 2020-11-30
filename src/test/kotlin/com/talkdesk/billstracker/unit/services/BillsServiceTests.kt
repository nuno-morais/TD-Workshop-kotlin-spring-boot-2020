package com.talkdesk.billstracker.unit.services

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.reset
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.exceptions.NotFoundException
import com.talkdesk.billstracker.gateways.BillsPublisher
import com.talkdesk.billstracker.repositories.BillsRepository
import com.talkdesk.billstracker.services.BillsService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.assertThrows
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.util.Optional

object BillsServiceTests : Spek({
	val repository = mock<BillsRepository>()
	val publisher = mock<BillsPublisher>()
	val subject = BillsService(repository, publisher)

	describe("#findAll") {
		it("should return the list of bills") {
			val expected = mock<List<Bill>>()

			whenever(repository.findAll()).thenReturn(expected)

			assertEquals(expected, subject.findAll())
		}
	}

	describe("#findById") {
		context("when the bill exists") {
			it("should return the bill") {
				val expected = mock<Bill>()
				val billMock = Optional.of(expected)

				whenever(repository.findById(1)).thenReturn(billMock)

				assertEquals(expected, subject.findById(1))
			}
		}

		context("when the bill does not exist") {
			it("should throw an exception") {
				val mock = Optional.empty<Bill>()
				whenever(repository.findById(1)).thenReturn(mock)

				assertThrows<NotFoundException> {
					subject.findById(1)
				}
			}
		}
	}

	describe("#create") {
		it("should create a new bill") {
			val bill = Bill(null, "Dummy Bill", 10, "acc")
			val expected = mock<Bill>()
			whenever(repository.save(bill)).thenReturn(expected)

			assertEquals(expected, subject.create(bill))
		}
	}

	describe("#update") {
		val bill = Bill(1, "Dummy", 100, "acc-1")

		it("should call the repository") {
			reset(repository)
			val expected = mock<Bill>()
			val billFinded = mock<Bill>()
			val findBillMock = Optional.of(billFinded)

			whenever(repository.findById(1)).thenReturn(findBillMock)
			whenever(repository.save(bill)).thenReturn(expected)

			subject.update(bill)
			verify(repository, times(1)).findById(1)
		}

		context("when the bill exists") {
			val expected = mock<Bill>()
			val billFinded = mock<Bill>()
			val findBillMock = Optional.of(billFinded)

			beforeEachGroup {
				reset(repository)
				whenever(repository.findById(1)).thenReturn(findBillMock)
			}

			it("should call the update on repository") {
				whenever(repository.save(bill)).thenReturn(expected)

				subject.update(bill)

				verify(repository, times(1)).save(bill)
			}

			it("should call the bills publisher") {
				reset(publisher)
				whenever(repository.save(bill)).thenReturn(expected)

				subject.update(bill)

				verify(publisher, times(1)).publishUpdateOption("acc-1")
			}

			it("should return the new bill") {
				whenever(repository.save(bill)).thenReturn(expected)

				assertEquals(expected, subject.update(bill))
			}
		}

		context("when the bill does not exist") {
			it("should throw an exception") {
				val findBillMock = Optional.empty<Bill>()
				whenever(repository.findById(1)).thenReturn(findBillMock)

				assertThrows<NotFoundException> {
					subject.update(bill)
				}
			}
		}
	}

	describe("#delete") {
		context("when the bill exists") {
			it("should delete the bill") {
				reset(repository)
				val billFinded = mock<Bill>().apply {
					whenever(id).thenReturn(1)
					whenever(accountId).thenReturn("account-id")
				}
				val findBillMock = Optional.of(billFinded)

				whenever(repository.findById(1)).thenReturn(findBillMock)

				subject.delete(1)
				verify(repository, times(1)).deleteById(1)
			}
		}

		context("when the bill does not exist") {
			it("should throw an exception") {
				val findBillMock = Optional.empty<Bill>()
				whenever(repository.findById(1)).thenReturn(findBillMock)

				assertThrows<NotFoundException> {
					subject.delete(1)
				}
			}
		}
	}
})