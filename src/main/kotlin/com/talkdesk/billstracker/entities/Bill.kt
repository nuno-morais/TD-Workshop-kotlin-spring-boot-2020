package com.talkdesk.billstracker.entities
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
@Entity
@Table
data class Bill(
		@Id
		@Column(name = "id")
		val id: Int? = null,
		@Column(name = "description")
		val description: String,
		@Column(name = "price")
		val price: Int,
		@Column(name = "accountId")
		val accountId: String
) {
	override fun toString() =
			String.format("ToDo(" +
					"id=%d, description='%s', price=%d, user_id='%s'" +
					")", id, description, price, accountId)
}