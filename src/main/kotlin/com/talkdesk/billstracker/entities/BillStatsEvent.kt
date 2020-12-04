package com.talkdesk.billstracker.entities

data class BillStatsEvent(
        val name: BillStatsOperation,
        val userId: String
)

enum class BillStatsOperation(val op: String) {
    CREATED("created"),
    UPDATED("updated"),
    DELETED("deleted");
}