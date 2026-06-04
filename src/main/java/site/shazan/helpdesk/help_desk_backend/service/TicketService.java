package site.shazan.helpdesk.help_desk_backend.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.shazan.helpdesk.help_desk_backend.entity.Ticket;
import site.shazan.helpdesk.help_desk_backend.repository.TicketRepo;

@Service
@Data
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepo ticketRepo;

    public Ticket createTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }
    public Ticket getTicket(Long ticketId) {
        return ticketRepo.findById(ticketId).orElse(null);
    }

    public Ticket getTicketByUsername(String username) {
        return ticketRepo.findByUsername(username).orElse(null);
    }


}
