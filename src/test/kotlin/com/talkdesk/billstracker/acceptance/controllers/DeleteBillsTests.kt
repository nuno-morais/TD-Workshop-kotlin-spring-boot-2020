package com.talkdesk.billstracker.acceptance.controllers

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
class DeleteBillsTests : BootstrapAcceptanceTests() {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `when perform a Delete bills, it returns ok`() {
        val id = bills[0].id!!
        val result = mockMvc.perform(
            MockMvcRequestBuilders.delete("/bills/$id")
                .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isNoContent)
    }

    @Test
    fun `when perform a Delete bills of a non existent bill, it returns not found`() {
        val result = mockMvc.perform(
            MockMvcRequestBuilders.delete("/bills/100")
                .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isNotFound)
    }
}