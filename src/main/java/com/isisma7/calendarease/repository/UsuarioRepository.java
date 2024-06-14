package com.isisma7.calendarease.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.isisma7.calendarease.models.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>{

    // Configuramos las funciones en este caso obtener los usuarios por su nombre
    Optional< Usuario > findUsuarioByNombreUsuario(String nombreUsuario);
}
