package com.talkdesk.billstracker.entities

data class BillStatEvent(val eventStatus : EventStatusCode,val userId : String  = "teste")

enum class EventStatusCode {
    DELETE, UPDATED,CREATED
}