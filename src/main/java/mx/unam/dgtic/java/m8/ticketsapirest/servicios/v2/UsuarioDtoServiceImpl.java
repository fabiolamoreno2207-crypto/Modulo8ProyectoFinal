package mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2;

import jakarta.transaction.Transactional;
import mx.unam.dgtic.java.m8.ticketsapirest.dtos.UsuarioDto;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.UsuarioRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.v2.interfaces.UsuarioDtoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioDtoServiceImpl implements UsuarioDtoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    private UsuarioDto entityToDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    private Usuario dtoToEntity(UsuarioDto dto) {
        return modelMapper.map(dto, Usuario.class);
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::entityToDto)
                .toList();
    }

    @Override
    public UsuarioDto obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        return entityToDto(usuario);
    }

    @Override
    public UsuarioDto guardar(UsuarioDto usuarioDto) {
        Usuario usuario = dtoToEntity(usuarioDto);
        Usuario saved = usuarioRepository.save(usuario);
        return entityToDto(saved);
    }

    @Override
    public UsuarioDto actualizarCompleto(Long id, UsuarioDto usuarioDto) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Forzamos actualizar todos los campos
        usuarioDb.setNombre(usuarioDto.getNombre());
        usuarioDb.setEmail(usuarioDto.getEmail());
        usuarioDb.setUser(usuarioDto.getUser());
        usuarioDb.setClave(usuarioDto.getClave());

        Usuario updated = usuarioRepository.save(usuarioDb);
        return entityToDto(updated);
    }

    @Override
    public UsuarioDto actualizarParcial(Long id, UsuarioDto cambios) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (cambios.getNombre() != null) usuarioDb.setNombre(cambios.getNombre());
        if (cambios.getEmail() != null) usuarioDb.setEmail(cambios.getEmail());
        if (cambios.getUser() != null) usuarioDb.setUser(cambios.getUser());
        if (cambios.getClave() != null) usuarioDb.setClave(cambios.getClave());

        Usuario updated = usuarioRepository.save(usuarioDb);
        return entityToDto(updated);
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuarioDb);
    }
}
