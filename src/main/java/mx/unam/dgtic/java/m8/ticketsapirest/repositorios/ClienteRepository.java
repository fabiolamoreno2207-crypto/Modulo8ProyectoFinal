package mx.unam.dgtic.java.m8.ticketsapirest.repositorios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
    Cliente findByNombre(String nombre);
}
