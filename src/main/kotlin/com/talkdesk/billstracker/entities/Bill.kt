package com.talkdesk.billstracker.entities

import org.jetbrains.annotations.NotNull
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
data class Bill(
	@Id
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