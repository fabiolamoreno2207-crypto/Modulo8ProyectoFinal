package mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> obtenerTodos();                     // GET
    Ticket obtenerTicketPorId(Long id);              // GET
    Ticket guardar(Long clienteId, Ticket ticket);// POST

    Ticket actualizarCompleto(Long id, Ticket ticket); // PUT
    Ticket actualizarParcial(Long id, Ticket ticket);  // PATCH

    Ticket eliminar(Long id);                        // DELETEList<Ticket> obtenerTodos();
    Ticket guardarClientePorTicket(Long clienteId, Ticket ticket);


}
