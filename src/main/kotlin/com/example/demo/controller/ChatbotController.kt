package com.example.demo.controller
import reactor.core.publisher.Mono
import com.example.demo.service.OpenAIService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatbotController(private val openAIService: OpenAIService) {

    @GetMapping("/chatbot")
    fun chat(@RequestParam prompt: String): Mono<String> {
        return openAIService.sendMessageToOpenAI(prompt)
    }
}
