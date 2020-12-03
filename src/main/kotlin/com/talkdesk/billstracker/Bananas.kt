package com.talkdesk.billstracker

import com.rabbitmq.client.ConnectionFactory
import java.nio.charset.StandardCharsets


fun main() {
    val factory = ConnectionFactory()
    factory.port = 5673
    factory.host = "localhost"
    factory.username = "test"
    factory.password = "test"
    factory.newConnection().use { connection ->
        connection.createChannel().use { channel ->
            channel.queueDeclare("stats", true, false, false, null)
            val message = "Hello World!"
            channel.basicPublish(
                    "",
                    "stats",
                    null,
                    message.toByteArray(StandardCharsets.UTF_8)
            )
        }
    }
}