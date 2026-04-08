package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ParteDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;

import java.util.List;

public interface ParteDtoService {
    List<ParteDto> obtenerTodos();
    ParteDto obtenerPorId(Long id);
    ParteDto guardar(ParteDto parteDto);
    ParteDto actualizarCompleto(Long id, ParteDto parteDto);
    ParteDto actualizarParcial(Long id, ParteDto parteDto);
    void eliminar(Long id);
}
