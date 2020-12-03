package com.talkdesk.billstracker.entities

import org.jetbrains.annotations.NotNull


data class Bill(
	val id: Int? = null,
	@NotNull
	val description: String,
	@NotNull
	val price: Int,
	@NotNull
	val accountId: String
) {
	override fun toString() =
		String.format("ToDo(" +
			"id=%d, description='%s', price=%d, user_id='%s'" +
			")", id, description, price, accountId)
}