package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;

import java.util.List;

public interface UsuarioDtoService {
    List<UsuarioDto> obtenerTodos();
    UsuarioDto obtenerPorId(Long id);
    UsuarioDto guardar(UsuarioDto usuarioDto);
    UsuarioDto actualizarCompleto(Long id, UsuarioDto usuarioDto);
    UsuarioDto actualizarParcial(Long id, UsuarioDto usuarioDto);
    void eliminar(Long id);        // DELETE

}
