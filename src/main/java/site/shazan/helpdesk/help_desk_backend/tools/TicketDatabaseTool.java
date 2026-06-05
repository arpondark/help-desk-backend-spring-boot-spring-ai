package site.shazan.helpdesk.help_desk_backend.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import site.shazan.helpdesk.help_desk_backend.entity.Priority;
import site.shazan.helpdesk.help_desk_backend.entity.Status;
import site.shazan.helpdesk.help_desk_backend.entity.Ticket;
import site.shazan.helpdesk.help_desk_backend.service.TicketService;

@Component
@RequiredArgsConstructor
public class TicketDatabaseTool {

    private final TicketService ticketService;

    // Look up an existing ticket by email. ALWAYS call this before creating a new one.
    @Tool(description = "Look up an existing help-desk ticket by the user's email. Returns the ticket if one exists, otherwise null. ALWAYS call this before createTicket to avoid duplicates.")
    public Ticket getTicketByEmail(
            @ToolParam(description = "The user's email address.") String email) {
        return ticketService.getTicketByEmailId(email);
    }

    // Create a new ticket
    @Tool(description = "Create a new help-desk ticket in the database. The email must be unique. Status defaults to OPEN. Returns the saved ticket with auto-generated id, createdOn, and updatedOn, or null if creation failed (e.g. duplicate email).")
    public Ticket createTicket(
            @ToolParam(description = "A short summary of the issue.") String summary,
            @ToolParam(description = "A detailed description of the issue as reported by the user.") String description,
            @ToolParam(description = "The category of the issue, e.g. ACCOUNT, NETWORK, HARDWARE, SOFTWARE.") String category,
            @ToolParam(description = "Priority of the ticket: LOW, MEDIUM, HIGH, or URGENT.") Priority priority,
            @ToolParam(description = "The user's email address. Must be unique and collected from the user.") String email,
            @ToolParam(description = "Initial status. Defaults to OPEN if omitted.", required = false) Status status) {
        try {
            System.out.println("going to create ticket for email=" + email);
            return ticketService.createTicket(description, summary, category, priority, email, status);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing ticket
    @Tool(description = "Update an existing ticket identified by id. Only the fields that are provided (non-null) will be updated. Returns the updated ticket, or null if no ticket with that id exists.")
    public Ticket updateTicket(
            @ToolParam(description = "The id of the ticket to update.") Long id,
            @ToolParam(description = "New short summary. Omit/pass null to keep existing.", required = false) String summary,
            @ToolParam(description = "New detailed description. Omit/pass null to keep existing.", required = false) String description,
            @ToolParam(description = "New category. Omit/pass null to keep existing.", required = false) String category,
            @ToolParam(description = "New priority: LOW, MEDIUM, HIGH, or URGENT. Omit/pass null to keep existing.", required = false) Priority priority,
            @ToolParam(description = "New email. Omit/pass null to keep existing.", required = false) String email,
            @ToolParam(description = "New status: OPEN, CLOSED, or RESOLVED. Omit/pass null to keep existing.", required = false) Status status) {
        return ticketService.updateTicket(id, description, summary, category, priority, email, status);
    }

    // Get current system time
    @Tool(description = "Get the current system time in epoch milliseconds.")
    public String getCurrentTime() {
        return String.valueOf(System.currentTimeMillis());
    }
}
