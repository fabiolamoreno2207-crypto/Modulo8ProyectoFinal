package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ProductoDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;

import java.util.List;

public interface ProductoDtoService {
    List<ProductoDto> obtenerTodos();
    ProductoDto obtenerPorId(Long id);
    ProductoDto guardar(ProductoDto productoDto);
    ProductoDto actualizarCompleto(Long id, ProductoDto productoDto);
    ProductoDto actualizarParcial(Long id, ProductoDto productoDto);
    void eliminar(Long id);
}
