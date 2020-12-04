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
	override fun toString(): String {
		return "Bill(id=$id, description='$description', price=$price, accountId='$accountId')"
	}
}