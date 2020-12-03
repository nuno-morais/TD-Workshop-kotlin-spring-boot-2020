package com.talkdesk.billstracker.configurations



import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig (
    private val rabbitmq : RabbitProperties
    ){

    //connection
    @Bean
    fun connection (connectionFactory : ConnectionFactory)= connectionFactory.newConnection()
    //connection factory
    @Bean
    fun connectionFactory() = ConnectionFactory().apply{
        username = rabbitmq.username
        password = rabbitmq.password
        host = rabbitmq.host
        port = rabbitmq.port
    }
    //channel
    fun channel(connection : Connection) = connection.createChannel()
}


@Configuration
@ConfigurationProperties(prefix="rabbitmq")
data class RabbitProperties(
    val username: String = "test",
    val password: String = "test",
    val port: Int = 5672,
    val host: String = "localhost",
    val queuename : String = "stats"
)