package com.isisma7.calendarease.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(unique = true, name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_no_expired")
    private boolean accountNoExpired;

    @Column(name = "account_no_locked")
    private boolean accountNoLocked;

    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_rol",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>(); //Set no permite repetidos al contrario de las listas

    // Se le asocia el medico
    @ManyToOne(targetEntity = Medico.class)
    @JoinColumn(name = "medico", nullable = true)
    private Paciente medico;

    // Se le asocia un paciente ya registrado
    @ManyToOne(targetEntity = Paciente.class)
    @JoinColumn(name = "paciente", nullable = true)
    private Paciente paciente;
}
