package com.talkdesk.billstracker.entities

data class Bill(
	val id: Int? = null,
	val description: String,
	val price: Int,
	val accountId: String
) {
	override fun toString(): String {
		return "Bill(id=$id, description='$description', price=$price, accountId='$accountId')"
	}
}