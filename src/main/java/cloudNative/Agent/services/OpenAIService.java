package cloudNative.Agent.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OpenAIService implements AgentServiceInterface {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIService.class);

    private final ChatClient chatClient;

    public OpenAIService(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    @RateLimiter(name = "openAIServiceRateLimiter", fallbackMethod = "handleRateLimitExceeded")
    public String request(String prompt) {
        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();
        return response;
    }

    public String handleRateLimitExceeded(String prompt, RequestNotPermitted e) {
        return "⚠️ Limite de 5 requisições por minuto atingido. Tente novamente mais tarde.";
    }
}