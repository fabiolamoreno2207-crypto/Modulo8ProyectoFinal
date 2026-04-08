package mx.unam.dgtic.java.m8.ticketsapirest.repositorios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
