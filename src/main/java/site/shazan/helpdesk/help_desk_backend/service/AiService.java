package site.shazan.helpdesk.help_desk_backend.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AiService {
    private final ChatClient chatClient;

    public String getResponseFromAssistant(String queary){
        return this.chatClient
                .prompt()
                .user(queary)
                .call()
                .content();
    }


}
