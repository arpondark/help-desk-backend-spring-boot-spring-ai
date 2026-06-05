package site.shazan.helpdesk.help_desk_backend.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("Summerize the response within 400 words and make it as simple as possible")
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }
}
