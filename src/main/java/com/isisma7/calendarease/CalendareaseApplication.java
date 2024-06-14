package com.isisma7.calendarease;

import java.util.Set;

import org.hibernate.mapping.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.isisma7.calendarease.models.Rol;
import com.isisma7.calendarease.models.RolEnum;
import com.isisma7.calendarease.models.Usuario;
import com.isisma7.calendarease.repository.UsuarioRepository;

import jakarta.persistence.criteria.Root;

@SuppressWarnings("unused")
@SpringBootApplication
public class CalendareaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalendareaseApplication.class, args);
	}

	@Bean
    CommandLineRunner init(UsuarioRepository usuarioRepository) {
        return args -> {
                // Crear el roles de usuarios
				Rol rolDev = Rol.builder()
					.rolName(RolEnum.DEV)
					.build();
				Rol rolDoc = Rol.builder()
					.rolName(RolEnum.DOC)
					.build();
				Rol rolPac = Rol.builder()
					.rolName(RolEnum.PAC)
					.build();
				Rol rolInv = Rol.builder()
					.rolName(RolEnum.INV)
					.build();
                

                // Crear el usuario con el rol de desarrollador
                Usuario userDev = Usuario.builder()
                    .nombreUsuario("calendar.dev")
                    .pass(new BCryptPasswordEncoder().encode("cal$987.dev#123")) // Encriptar la contraseña
                    .isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(rolDev))
					.medico(null)
					.paciente(null)
                    .build();

				// Guardar todos los usuarios
				usuarioRepository.save(userDev);
        };
    }

}
