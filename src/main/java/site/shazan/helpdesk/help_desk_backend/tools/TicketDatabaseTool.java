package site.shazan.helpdesk.help_desk_backend.tools;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import site.shazan.helpdesk.help_desk_backend.entity.Ticket;
import site.shazan.helpdesk.help_desk_backend.service.TicketService;

@Component
@RequiredArgsConstructor
public class TicketDatabaseTool {
    private final TicketService ticketService;



    //create
    @Tool(description = "This tool helps to create new ticket in database.")
    public Ticket createTicketTool(@ToolParam(description = "Ticket details.") Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    //get ticket using username
    public Ticket getTicketByUserName(@ToolParam(description = "Ticket details.") String username) {
        return ticketService.getTicketByUsername(username);
    }

    public Ticket UpdateTicket(@ToolParam(description = "New Ticket details with old ticket id.") Ticket ticket) {
        return ticketService.updateTicket(ticket);
    }

    //get current system time
    @Tool(description = "This tool helps to get current system time.")
    public String getCurrentTime(){
        return String.valueOf(System.currentTimeMillis());
    }

}
