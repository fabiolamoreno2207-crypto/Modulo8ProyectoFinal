package mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;

import java.util.List;

public interface ParteService {
    List<Parte> obtenerTodos();

    Parte obtenerPartePorId(Long id);

    List<Parte> obtenerPorProductoId(Long productoId);

    Parte guardar(Long productoId, Parte parte);

    Parte actualizarCompleto(Long idParte, Parte parte);

    Parte actualizarParcial(Long idParte, Parte parte);

    Parte eliminar(Long idParte);
}
