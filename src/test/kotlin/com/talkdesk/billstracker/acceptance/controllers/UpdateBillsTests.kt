package com.talkdesk.billstracker.acceptance.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.talkdesk.billstracker.entities.Bill
import org.junit.jupiter.api.Assertions
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
class UpdateBillsTests : BootstrapAcceptanceTests() {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Test
	fun `when perform an UPDATE bills, it returns the updated bill`() {
		val id = bills[0].id!!
		val expected = Bill(
			id = id,
			description = "Updated dummy bill",
			price = 100,
			accountId = "acc-1"
		)

		val result = mockMvc.perform(
			MockMvcRequestBuilders.put("/bills/$id")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(expected))
		)

		result.andExpect(status().isOk)
		val content = objectMapper.readValue(
			result.andReturn().response.contentAsString, Bill::class.java
		)

		Assertions.assertEquals(expected, content)
	}

	@Test
	fun `when perform an Update bills with a non valid fields, it returns bad request`() {
		val id = bills[0].id!!
		val result = mockMvc.perform(
			MockMvcRequestBuilders.put("/bills/$id")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
						"id": 1,
						"price": 100,
						"account_id": "acc-1"
					}
				""".trimIndent())
		)
		result.andExpect(status().isBadRequest)
	}

	@Test
	fun `when perform an Update bills with a non existent bill, it returns not found`() {
		val bill = Bill(
			id = 100,
			description = "Updated dummy bill",
			price = 100,
			accountId = "acc-1"
		)
		val result = mockMvc.perform(
			MockMvcRequestBuilders.put("/bills/100")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bill))
		)
		result.andExpect(status().isNotFound)
	}
}