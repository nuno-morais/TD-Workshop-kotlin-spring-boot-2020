package com.talkdesk.billstracker.acceptance.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class GetByIdBillsTests : BootstrapAcceptanceTests() {
	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Test
	fun `when perform a GET bills, it returns the bill`() {
		val id = bills[0].id!!
		val result = mockMvc.perform(
			MockMvcRequestBuilders.get("/bills/$id")
				.contentType(MediaType.APPLICATION_JSON)
		)

		result.andExpect(status().isOk)
			.andExpect(content().json(objectMapper.writeValueAsString(bills[0]), true))
	}

	@Test
	fun `when perform a GET bills of a non existent bill, it returns not found`() {
		val result = mockMvc.perform(
			MockMvcRequestBuilders.get("/bills/100")
				.contentType(MediaType.APPLICATION_JSON)
		)

		result.andExpect(status().isNotFound)
	}
}