package mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();                     // GET
    Producto obtenerProductoPorId(Long id);            // GET
    Producto guardar(Producto producto);               // POST

    Producto actualizarCompleto(Long id, Producto producto); // PUT
    Producto actualizarParcial(Long id, Producto producto);  // PATCH

    Producto eliminar(Long id);                        // DELETE
}
