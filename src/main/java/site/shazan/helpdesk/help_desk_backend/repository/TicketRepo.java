package site.shazan.helpdesk.help_desk_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.shazan.helpdesk.help_desk_backend.entity.Ticket;

import java.util.Optional;

public interface TicketRepo extends JpaRepository<Ticket, Long> {
    //Optional<Ticket> findByTicketId(Long ticketId);
    Optional<Ticket> findByUsername(String username);
}
