package site.shazan.helpdesk.help_desk_backend.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import site.shazan.helpdesk.help_desk_backend.tools.TicketDatabaseTool;

@Service
@Data
@RequiredArgsConstructor
public class AiService {
    private final ChatClient chatClient;
    private final TicketDatabaseTool ticketDatabaseTool;

    public String getResponseFromAssistant(String queary){
        return this.chatClient
                .prompt()
                .tools(ticketDatabaseTool)
                .user(queary)
                .call()
                .content();
    }


}
