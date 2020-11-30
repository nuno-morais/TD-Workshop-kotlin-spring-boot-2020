package com.talkdesk.billstracker.entities

data class Bill(
	val id: Int? = null,
	val description: String,
	val price: Int,
	val accountId: String
) {
	override fun toString() =
		String.format("ToDo(" +
			"id=%d, description='%s', price=%d, user_id='%s'" +
			")", id, description, price, accountId)
}