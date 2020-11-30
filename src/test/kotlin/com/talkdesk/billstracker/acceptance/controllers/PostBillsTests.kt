package com.talkdesk.billstracker.acceptance.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.talkdesk.billstracker.entities.Bill
import com.talkdesk.billstracker.repositories.BillsRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class PostBillsTests : BootstrapAcceptanceTests() {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Autowired
	private lateinit var billsRepository: BillsRepository

	@Test
	fun `when perform a POST bills, it returns the new bill`() {
		val newBill = Bill(
			description = "Create dummy bill",
			price = 100,
			accountId = "100"
		)
		val result = mockMvc.perform(
			MockMvcRequestBuilders.post("/bills")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(newBill))
		)

		result.andExpect(status().isCreated)
		val content = objectMapper.readValue(
			result.andReturn().response.contentAsString, Bill::class.java
		)
		assertEquals(billsRepository.findById(content.id!!).get(), content)
	}

	@Test
	fun `when perform a POST bills with a non valid fields, it returns bad request`() {
		val result = mockMvc.perform(
			MockMvcRequestBuilders.post("/bills")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"price": 100,
						"account_id": "acc-1"
					}
				""".trimIndent())
		)
		result.andExpect(status().isBadRequest)
	}
}