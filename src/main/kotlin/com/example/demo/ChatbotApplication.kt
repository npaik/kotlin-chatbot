package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.reactive.function.client.WebClient

@SpringBootApplication
class ChatbotApplication {

    @Bean
    fun webClient(): WebClient {
        return WebClient.builder()
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<ChatbotApplication>(*args)
}
