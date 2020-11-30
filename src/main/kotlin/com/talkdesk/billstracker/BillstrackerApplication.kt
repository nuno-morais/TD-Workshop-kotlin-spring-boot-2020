package com.talkdesk.billstracker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BillstrackerApplication

fun main(args: Array<String>) {
	runApplication<BillstrackerApplication>(*args)
}
