package com.talkdesk.billstracker.controllers

import com.talkdesk.billstracker.exceptions.NotFoundException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {
	@ExceptionHandler(Exception::class)
	fun catchAll(exception: Exception, request: WebRequest): ResponseEntity<Any> {
		val body = """
			{
			    "message": "Unhandled exception"
			}
		""".trimIndent()
		return handleExceptionInternal(
			exception, body, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request
		)
	}

	@ExceptionHandler(NotFoundException::class)
	fun handleNotFound(exception: NotFoundException, request: WebRequest): ResponseEntity<Any> {
		val body = """
			{
			    "message": "Not found"
			}
		""".trimIndent()
		return handleExceptionInternal(
			exception, body, HttpHeaders(), HttpStatus.NOT_FOUND, request
		)
	}
}