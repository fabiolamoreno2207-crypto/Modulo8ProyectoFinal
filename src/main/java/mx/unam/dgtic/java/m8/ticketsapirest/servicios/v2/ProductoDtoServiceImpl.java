package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ProductoDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ProductoRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ProductoService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.ProductoDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductoDtoServiceImpl implements ProductoDtoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ProductoDto entityToDto(Producto producto) {
        return modelMapper.map(producto, ProductoDto.class);
    }

    private Producto dtoToEntity(ProductoDto dto) {
        return modelMapper.map(dto, Producto.class);
    }

    @Override
    public List<ProductoDto> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ProductoDto obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return entityToDto(producto);
    }

    @Override
    public ProductoDto guardar(ProductoDto productoDto) {
        Producto producto = dtoToEntity(productoDto);
        Producto saved = productoRepository.save(producto);
        return entityToDto(saved);
    }

    @Override
    public ProductoDto actualizarCompleto(Long id, ProductoDto productoDto) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        productoDb.setSku(productoDto.getSku());
        productoDb.setNombre(productoDto.getNombre());
        productoDb.setSerie(productoDto.getSerie());
        productoDb.setUbicacion(productoDto.getUbicacion());

        Producto updated = productoRepository.save(productoDb);
        return entityToDto(updated);
    }

    @Override
    public ProductoDto actualizarParcial(Long id, ProductoDto cambios) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        if (cambios.getSku() != null) productoDb.setSku(cambios.getSku());
        if (cambios.getNombre() != null) productoDb.setNombre(cambios.getNombre());
        if (cambios.getSerie() != null) productoDb.setSerie(cambios.getSerie());
        if (cambios.getUbicacion() != null) productoDb.setUbicacion(cambios.getUbicacion());

        Producto updated = productoRepository.save(productoDb);
        return entityToDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        Producto productoDb = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        productoRepository.delete(productoDb);
    }
}
