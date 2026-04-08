package mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Cliente;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;

import java.util.List;

public interface ClienteService {
    List<Cliente> obtenerTodos();                  // GET
    Cliente obtenerClientePorId(Long id);          // GET
    Cliente guardar(Cliente cliente);              // POST

    List<Cliente> obtenerClientePorTicketId(Long ticketId);

    Cliente actualizarCompleto(Long id, Cliente cliente); // PUT
    Cliente actualizarParcial(Long id, Cliente cliente);  // PATCH

    Cliente eliminar(Long id);                     // DELETE

}
