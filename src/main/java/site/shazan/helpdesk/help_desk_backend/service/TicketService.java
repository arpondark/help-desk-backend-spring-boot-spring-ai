package site.shazan.helpdesk.help_desk_backend.service;

import jakarta.transaction.Transactional;
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

    @Transactional
    public Ticket createTicket(Ticket ticket) {
       // ticket.setId(null); //force fully adding the null so it create id automatically
        return ticketRepo.save(ticket);
    }
    public Ticket getTicket(Long ticketId) {
        return ticketRepo.findById(ticketId).orElse(null);
    }
    @Transactional
    public Ticket updateTicket(Ticket ticket) {
        return ticketRepo.save(ticket);
    }

    public Ticket getTicketByEmailId(String email) {
        return ticketRepo.findByEmail(email).orElse(null);
    }


}
