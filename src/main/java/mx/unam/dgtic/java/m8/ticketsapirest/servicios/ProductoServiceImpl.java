package mx.unam.dgtic.java.m8.ticketsapirest.servicios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ProductoRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    @Override
    public Producto guardar(Producto producto) {
        // Si el producto viene con partes, aseguramos la relación
        if (producto.getPartes() != null) {
            producto.getPartes().forEach(parte -> parte.setProducto(producto));
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarCompleto(Long id, Producto productoNuevo) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // Forzamos actualizar todos los campos simples
        productoDb.setSku(productoNuevo.getSku());
        productoDb.setNombre(productoNuevo.getNombre());
        productoDb.setSerie(productoNuevo.getSerie());
        productoDb.setUbicacion(productoNuevo.getUbicacion());

        // Actualizamos la relación con Partes
        if (productoNuevo.getPartes() != null) {
            // Creamos una nueva lista para evitar referencias viejas
            productoDb.setPartes(new ArrayList<>(productoNuevo.getPartes()));
            // Aseguramos que cada parte apunte al producto actualizado
            for (Parte parte : productoDb.getPartes()) {
                parte.setProducto(productoDb);
            }
        } else {
            productoDb.setPartes(null);
        }

        return productoRepository.save(productoDb);
    }

    @Override
    public Producto actualizarParcial(Long id, Producto cambios) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (cambios.getSku() != null) productoDb.setSku(cambios.getSku());
        if (cambios.getNombre() != null) productoDb.setNombre(cambios.getNombre());
        if (cambios.getSerie() != null) productoDb.setSerie(cambios.getSerie());
        if (cambios.getUbicacion() != null) productoDb.setUbicacion(cambios.getUbicacion());
        // No actualizamos partes en parcial

        return productoRepository.save(productoDb);
    }

    @Override
    public Producto eliminar(Long id) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        productoRepository.delete(productoDb);
        return productoDb;
    }
}
