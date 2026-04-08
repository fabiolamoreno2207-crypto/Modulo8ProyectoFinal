package mx.unam.dgtic.java.m8.ticketsapirest.repositorios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
