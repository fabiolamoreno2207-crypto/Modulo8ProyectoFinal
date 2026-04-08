package mx.unam.dgtic.java.m8.ticketsapirest.servicios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Cliente;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Ticket;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ClienteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.TicketRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Ticket> obtenerTodos() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket obtenerTicketPorId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));
    }

    @Override
    public Ticket guardar(Long clienteId, Ticket ticket) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));
        ticket.setCliente(cliente);
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket actualizarCompleto(Long id, Ticket ticketNuevo) {
        Ticket ticketDb = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));

        // Forzamos actualizar todos los campos simples
        ticketDb.setFolio(ticketNuevo.getFolio());
        ticketDb.setFecha(ticketNuevo.getFecha());

        // Actualizamos la relación con Cliente
        if (ticketNuevo.getCliente() != null) {
            Cliente cliente = clienteRepository.findById(ticketNuevo.getCliente().getId_cliente())
                    .orElseThrow(() -> new RuntimeException(
                            "Cliente no encontrado con ID: " + ticketNuevo.getCliente().getId_cliente()));
            ticketDb.setCliente(cliente);
        } else {
            ticketDb.setCliente(null);
        }

        // Actualizamos la relación con Productos
        if (ticketNuevo.getProductos() != null) {
            ticketDb.setProductos(new ArrayList<>(ticketNuevo.getProductos()));
            for (Producto producto : ticketDb.getProductos()) {
                producto.getTickets().add(ticketDb); // aseguramos la relación inversa
            }
        } else {
            ticketDb.setProductos(null);
        }

        return ticketRepository.save(ticketDb);
    }

    @Override
    public Ticket actualizarParcial(Long id, Ticket cambios) {
        Ticket ticketDb = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));

        if (cambios.getFolio() != null) ticketDb.setFolio(cambios.getFolio());
        if (cambios.getFecha() != null) ticketDb.setFecha(cambios.getFecha());
        if (cambios.getCliente() != null) ticketDb.setCliente(cambios.getCliente());
        if (cambios.getProductos() != null) ticketDb.setProductos(cambios.getProductos());

        return ticketRepository.save(ticketDb);
    }

    @Override
    public Ticket eliminar(Long id) {
        Ticket ticketDb = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + id));
        ticketRepository.delete(ticketDb);
        return ticketDb;
    }

    @Override
    public Ticket guardarClientePorTicket(Long clienteId, Ticket ticket) {
        // Buscar el cliente en la base de datos
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + clienteId));

        // Asociar el cliente al ticket
        ticket.setCliente(cliente);

        // Guardar el ticket en la base de datos
        return ticketRepository.save(ticket);
    }

}
