package com.talkdesk.billstracker.configurations

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ObjectMapperConfiguration {
	@Bean
	fun objectMapper() = ObjectMapper().apply {
		findAndRegisterModules()
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
		setSerializationInclusion(JsonInclude.Include.NON_NULL)
		propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
	}
}