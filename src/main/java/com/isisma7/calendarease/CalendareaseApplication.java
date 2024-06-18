package com.isisma7.calendarease;

import java.util.List;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.isisma7.calendarease.models.Rol;
import com.isisma7.calendarease.models.RolEnum;
import com.isisma7.calendarease.models.Usuario;
import com.isisma7.calendarease.repository.UsuarioRepository;

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

				// Crear el usuario con el rol de desarrollador
				Usuario userDoc = Usuario.builder()
					.nombreUsuario("calendar.doc")
					.pass(new BCryptPasswordEncoder().encode("cal$987.doc#123")) // Encriptar la contraseña
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(rolDoc))
					.medico(null)
					.paciente(null)
					.build();

				// Crear el usuario con el rol de desarrollador
				Usuario userPac = Usuario.builder()
					.nombreUsuario("calendar.pac")
					.pass(new BCryptPasswordEncoder().encode("cal$987.pac#123")) // Encriptar la contraseña
					.isEnabled(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(rolPac))
					.medico(null)
					.paciente(null)
					.build();

				// Guardar todos los usuarios
				usuarioRepository.saveAll(List.of(userDev, userDoc, userPac));
        };
    }

}
