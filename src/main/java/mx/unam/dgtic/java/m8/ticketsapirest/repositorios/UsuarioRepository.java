package mx.unam.dgtic.java.m8.ticketsapirest.repositorios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

}
