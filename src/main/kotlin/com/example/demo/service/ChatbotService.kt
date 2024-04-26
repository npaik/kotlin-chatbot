package com.example.demo.service

import org.springframework.stereotype.Service

@Service
class ChatbotService {
    fun getResponse(message: String): String {
        return when {
            "hello" in message.lowercase() -> "Hello! How can I help you today?"
            "help" in message.lowercase() -> "I can assist you with your account and general inquiries."
            else -> "Sorry, I don't understand that. Can you please rephrase?"
        }
    }
}
