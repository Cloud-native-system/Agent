package cloudNative.Agent.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Qualifier;

@Service
public class OpenAIService implements AgentServiceInterface {

    private final ChatClient chatClient;

    public OpenAIService(@Qualifier("openAiChatClient") ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @Override
    public String request(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();

    }
}
