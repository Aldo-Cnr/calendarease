package com.isisma7.calendarease.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.isisma7.calendarease.models.Usuario;
import com.isisma7.calendarease.repository.UsuarioRepository;
@SuppressWarnings("unused")
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    // Inyeccion del repositorio
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Buscar usuario por su nombre
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // Opcion del repositorio
        Usuario usuario = usuarioRepository.findUsuarioByNombreUsuario(username)
        .orElseThrow(()-> new UsernameNotFoundException("El usuario " + username + " NO EXISTE!!!"));

        // Lista de autorizacion (ROLES)
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        usuario.getRoles()
        .forEach(rol ->
        authorityList.add(new SimpleGrantedAuthority(
            "ROLE_".concat(rol.getRolName().name())
        )));

        return new User(usuario.getNombreUsuario(),
            usuario.getPass(),
            usuario.isEnabled(),
            usuario.isAccountNoExpired(),
            usuario.isCredentialNoExpired(),
            usuario.isAccountNoLocked(),
            authorityList
        );
    }

}
