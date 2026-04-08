package mx.unam.dgtic.java.m8.ticketsapirest.servicios;

import jakarta.transaction.Transactional;
import mx.unam.dgtic.java.m8.ticketsapirest.entidades.Usuario;
import mx.unam.dgtic.java.m8.ticketsapirest.repositorios.UsuarioRepository;
import mx.unam.dgtic.java.m8.ticketsapirest.servicios.interfaces.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizarCompleto(Long id, Usuario usuarioNuevo) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        // Forzamos actualizar todos los campos
        usuarioDb.setNombre(usuarioNuevo.getNombre());
        usuarioDb.setEmail(usuarioNuevo.getEmail());
        usuarioDb.setUser(usuarioNuevo.getUser());
        usuarioDb.setClave(usuarioNuevo.getClave());

        return usuarioRepository.save(usuarioDb);
    }

    @Override
    public Usuario actualizarParcial(Long id, Usuario cambios) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));

        if (cambios.getNombre() != null) usuarioDb.setNombre(cambios.getNombre());
        if (cambios.getEmail() != null) usuarioDb.setEmail(cambios.getEmail());
        if (cambios.getUser() != null) usuarioDb.setUser(cambios.getUser());
        if (cambios.getClave() != null) usuarioDb.setClave(cambios.getClave());

        return usuarioRepository.save(usuarioDb);
    }

    @Override
    public Usuario eliminar(Long id) {
        Usuario usuarioDb = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        usuarioRepository.delete(usuarioDb);
        return usuarioDb;
    }
}
