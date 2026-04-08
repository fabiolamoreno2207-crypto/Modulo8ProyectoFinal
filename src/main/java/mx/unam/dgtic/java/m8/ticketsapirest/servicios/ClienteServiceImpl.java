package mx.unam.dgtic.java.m8.ticketsapirest.servicios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Cliente;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Ticket;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ClienteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.TicketRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ClienteService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public Cliente guardar(Cliente cliente) {
        // Si el cliente viene con tickets, aseguramos la relación
        if (cliente.getTickets() != null) {
            cliente.getTickets().forEach(ticket -> ticket.setCliente(cliente));
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> obtenerClientePorTicketId(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado con ID: " + ticketId));

        // Un ticket tiene un solo cliente, pero como el método devuelve lista, lo envolvemos
        return List.of(ticket.getCliente());
    }

    @Override
    public Cliente actualizarCompleto(Long id, Cliente clienteNuevo) {
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        // Forzamos actualizar todos los campos simples
        clienteDb.setNombre(clienteNuevo.getNombre());
        clienteDb.setEmail(clienteNuevo.getEmail());
        clienteDb.setTelefono(clienteNuevo.getTelefono());

        // Actualizamos la relación con Tickets
        if (clienteNuevo.getTickets() != null) {
            clienteDb.setTickets(new ArrayList<>(clienteNuevo.getTickets()));
            for (Ticket ticket : clienteDb.getTickets()) {
                ticket.setCliente(clienteDb);
            }
        } else {
            clienteDb.setTickets(null);
        }

        return clienteRepository.save(clienteDb);
    }

    @Override
    public Cliente actualizarParcial(Long id, Cliente cambios) {
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));

        if (cambios.getNombre() != null) clienteDb.setNombre(cambios.getNombre());
        if (cambios.getEmail() != null) clienteDb.setEmail(cambios.getEmail());
        if (cambios.getTelefono() != null) clienteDb.setTelefono(cambios.getTelefono());
        // No actualizamos tickets en parcial

        return clienteRepository.save(clienteDb);
    }

    @Override
    public Cliente eliminar(Long id) {
        Cliente clienteDb = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
        clienteRepository.delete(clienteDb);
        return clienteDb;
    }
}
