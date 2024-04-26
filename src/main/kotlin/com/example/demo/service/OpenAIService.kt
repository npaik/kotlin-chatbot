package com.example.demo.service
import reactor.core.publisher.Mono
import org.springframework.http.MediaType
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono

@Service
class OpenAIService(private val webClient: WebClient) {

    @Value("\${openai.api.url}")
    private lateinit var openaiApiUrl: String

    @Value("\${openai.api.key}")
    private lateinit var openaiApiKey: String

    fun sendMessageToOpenAI(prompt: String): Mono<String> {
        val requestBody = mapOf(
            "model" to "gpt-3.5-turbo", 
            "messages" to listOf(mapOf(
                "role" to "user",
                "content" to prompt
            )),
            "max_tokens" to 150,
            "temperature" to 0.7
        )

        return webClient.post()
            .uri(openaiApiUrl)
            .header(HttpHeaders.AUTHORIZATION, "Bearer $openaiApiKey")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .retrieve()
            .onStatus({ status -> !status.is2xxSuccessful }) { response ->
                response.bodyToMono<String>().flatMap { errorMessage ->
                    Mono.error(IllegalStateException("Error from OpenAI API: $errorMessage"))
                }
            }
            .bodyToMono<String>()
            .doOnError { e -> e.printStackTrace() } 
    }
}
