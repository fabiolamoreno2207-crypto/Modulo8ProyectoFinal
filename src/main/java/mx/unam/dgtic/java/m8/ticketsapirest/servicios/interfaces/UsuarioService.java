package mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> obtenerTodos();              // GET
    Usuario obtenerUsuarioPorId(Long id);      // GET
    Usuario guardar(Usuario usuario);          // POST

    Usuario actualizarCompleto(Long id, Usuario usuario); // PUT
    Usuario actualizarParcial(Long id, Usuario usuario);  // PATCH

    Usuario eliminar(Long id);                 // DELETE

}
