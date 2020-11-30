package com.talkdesk.billstracker.entities

data class BillStatsEvent(
	val name: StatsOperation = StatsOperation.CREATED,
	val userId: String = ""
)

enum class StatsOperation(val op: String) {
	CREATED("created"),
	UPDATED("updated"),
	Deleted("deleted")
}
