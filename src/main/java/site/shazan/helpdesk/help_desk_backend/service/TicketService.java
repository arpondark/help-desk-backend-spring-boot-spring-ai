package site.shazan.helpdesk.help_desk_backend.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.shazan.helpdesk.help_desk_backend.entity.Priority;
import site.shazan.helpdesk.help_desk_backend.entity.Status;
import site.shazan.helpdesk.help_desk_backend.entity.Ticket;
import site.shazan.helpdesk.help_desk_backend.repository.TicketRepo;

@Service
@Data
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;

    @Transactional
    public Ticket createTicket(String description,
                               String summary,
                               String category,
                               Priority priority,
                               String email,
                               Status status) {
        Ticket ticket = Ticket.builder()
                .description(description)
                .summary(summary)
                .category(category)
                .priority(priority)
                .email(email)
                .status(status == null ? Status.OPEN : status)
                .build();
        return ticketRepo.save(ticket);
    }

    public Ticket getTicket(Long ticketId) {
        return ticketRepo.findById(ticketId).orElse(null);
    }

    @Transactional
    public Ticket updateTicket(Long id,
                               String description,
                               String summary,
                               String category,
                               Priority priority,
                               String email,
                               Status status) {
        Ticket existing = ticketRepo.findById(id).orElse(null);
        if (existing == null) {
            return null;
        }
        if (description != null) existing.setDescription(description);
        if (summary != null) existing.setSummary(summary);
        if (category != null) existing.setCategory(category);
        if (priority != null) existing.setPriority(priority);
        if (email != null) existing.setEmail(email);
        if (status != null) existing.setStatus(status);
        return ticketRepo.save(existing);
    }

    public Ticket getTicketByEmailId(String email) {
        return ticketRepo.findByEmail(email).orElse(null);
    }
}
