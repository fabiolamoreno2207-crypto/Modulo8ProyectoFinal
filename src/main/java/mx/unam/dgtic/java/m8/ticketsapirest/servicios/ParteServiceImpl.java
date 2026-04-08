package mx.unam.dgtic.java.m8.ticketsapirest.servicios;

import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ParteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ProductoRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ParteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParteServiceImpl implements ParteService {
    @Autowired
    private ParteRepository parteRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Parte> obtenerTodos() {
        return parteRepository.findAll();
    }

    @Override
    public Parte obtenerPartePorId(Long id) {
        return parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));
    }

    @Override
    public List<Parte> obtenerPorProductoId(Long productoId) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + productoId));
        return producto.getPartes();
    }

    @Override
    public Parte guardar(Long productoId, Parte parte) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + productoId));
        parte.setProducto(producto); // integridad referencial
        return parteRepository.save(parte);
    }


    @Override
    public Parte actualizarCompleto(Long id, Parte parteNueva) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));

        // Forzamos actualizar todos los campos simples
        parteDb.setSku(parteNueva.getSku());
        parteDb.setDescripcion(parteNueva.getDescripcion());
        parteDb.setUbicacion(parteNueva.getUbicacion());
        parteDb.setStock(parteNueva.getStock());

        // Actualizamos la relación con Producto si viene en el objeto nuevo
        if (parteNueva.getProducto() != null) {
            Producto producto = productoRepository.findById(parteNueva.getProducto().getId_producto())
                    .orElseThrow(() -> new RuntimeException(
                            "Producto no encontrado con ID: " + parteNueva.getProducto().getId_producto()));
            parteDb.setProducto(producto);
        } else {
            parteDb.setProducto(null);
        }

        return parteRepository.save(parteDb);
    }

    @Override
    public Parte actualizarParcial(Long id, Parte cambios) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));

        if (cambios.getSku() != null) parteDb.setSku(cambios.getSku());
        if (cambios.getDescripcion() != null) parteDb.setDescripcion(cambios.getDescripcion());
        if (cambios.getUbicacion() != null) parteDb.setUbicacion(cambios.getUbicacion());
        if (cambios.getStock() != null) parteDb.setStock(cambios.getStock());
        if (cambios.getProducto() != null) parteDb.setProducto(cambios.getProducto());

        return parteRepository.save(parteDb);
    }

    @Override
    public Parte eliminar(Long id) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));
        parteRepository.delete(parteDb);
        return parteDb;
    }
}
