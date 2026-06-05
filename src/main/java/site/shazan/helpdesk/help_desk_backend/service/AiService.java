package site.shazan.helpdesk.help_desk_backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import site.shazan.helpdesk.help_desk_backend.tools.TicketDatabaseTool;

@Service
@RequiredArgsConstructor
public class AiService {
    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketDatabaseTool;

    @Value("classpath:/helpdesk-system.st")
    private Resource systemPrompt;

    public String getResponseFromAssistant(String query){
        return this.chatClient
                .prompt()
                .tools(ticketDatabaseTool)
                .system(systemPrompt)
                .user(query)
                .call()
                .content();
    }
}
