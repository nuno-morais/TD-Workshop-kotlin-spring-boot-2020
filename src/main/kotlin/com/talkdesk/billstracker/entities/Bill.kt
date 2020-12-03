package com.talkdesk.billstracker.entities

import javax.persistence.*

@Entity
@Table(name = "bill")
data class Bill(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(name = "description")
        val description: String,

        @Column(name = "price")
        val price: Int,

        @Column(name = "account_id")
        val accountId: String
) {
    override fun toString() =
            String.format("ToDo(" +
                    "id=%d, description='%s', price=%d, user_id='%s'" +
                    ")", id, description, price, accountId)
}

//data class Bill(
//		val id: Int? = null,
//		val description: String,
//		val price: Int,
//		val accountId: String
//) {
//	override fun toString() =
//			String.format("ToDo(" +
//					"id=%d, description='%s', price=%d, user_id='%s'" +
//					")", id, description, price, accountId)
//}