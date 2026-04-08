package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import mx.unam.dgtic.java.m8.ticketsapirest.dtos.ParteDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Parte;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Producto;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ParteRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.ProductoRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.ParteService;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.ParteDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ParteDtoServiceImpl implements ParteDtoService {
    @Autowired
    private ParteRepository parteRepository;

    @Autowired
    private ModelMapper modelMapper;

    private ParteDto entityToDto(Parte parte) {
        return modelMapper.map(parte, ParteDto.class);
    }

    private Parte dtoToEntity(ParteDto dto) {
        return modelMapper.map(dto, Parte.class);
    }

    @Override
    public List<ParteDto> obtenerTodos() {
        return parteRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public ParteDto obtenerPorId(Long id) {
        Parte parte = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));
        return entityToDto(parte);
    }

    @Override
    public ParteDto guardar(ParteDto parteDto) {
        Parte parte = dtoToEntity(parteDto);
        Parte saved = parteRepository.save(parte);
        return entityToDto(saved);
    }

    @Override
    public ParteDto actualizarCompleto(Long id, ParteDto parteDto) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));

        parteDb.setSku(parteDto.getSku());
        parteDb.setDescripcion(parteDto.getDescripcion());
        parteDb.setUbicacion(parteDto.getUbicacion());
        parteDb.setStock(parteDto.getStock());

        Parte updated = parteRepository.save(parteDb);
        return entityToDto(updated);
    }

    @Override
    public ParteDto actualizarParcial(Long id, ParteDto cambios) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));

        if (cambios.getSku() != null) parteDb.setSku(cambios.getSku());
        if (cambios.getDescripcion() != null) parteDb.setDescripcion(cambios.getDescripcion());
        if (cambios.getUbicacion() != null) parteDb.setUbicacion(cambios.getUbicacion());
        if (cambios.getStock() != null) parteDb.setStock(cambios.getStock());

        Parte updated = parteRepository.save(parteDb);
        return entityToDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        Parte parteDb = parteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parte no encontrada con ID: " + id));
        parteRepository.delete(parteDb);
    }
}
