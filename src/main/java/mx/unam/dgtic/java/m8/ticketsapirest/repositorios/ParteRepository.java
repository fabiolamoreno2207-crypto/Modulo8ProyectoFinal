package mx.unam.dgtic.java.m8.ticketsapirest.repositorios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParteRepository extends JpaRepository<Parte, Long> {
}
