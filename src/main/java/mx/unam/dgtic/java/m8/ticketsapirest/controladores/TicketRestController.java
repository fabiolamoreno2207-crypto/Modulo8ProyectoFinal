package mx.unam.dgtic.java.m8.ticketsapirest.controladores;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Ticket;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.TicketRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> obtenerTickets() {
        return ResponseEntity.ok(ticketService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.obtenerTicketPorId(id));
    }

    @PostMapping("/cliente/{clienteId}")
    public ResponseEntity<Ticket> insertar(@PathVariable Long clienteId, @RequestBody Ticket ticket) {
        Ticket nuevo = ticketService.guardar(clienteId, ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.actualizarCompleto(id, ticket));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Ticket> actualizarParcial(@PathVariable Long id, @RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.actualizarParcial(id, ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Ticket> eliminar(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.eliminar(id));
    }
}
