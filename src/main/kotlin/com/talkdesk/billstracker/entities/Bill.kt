package com.talkdesk.billstracker.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Bill(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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