package com.isisma7.calendarease.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medico")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private Long idMedico;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "ape_paterno", nullable = false)
    private String apePaterno;

    @Column(name = "ape_materno", nullable = false)
    private String apeMaterno;

    @Column(name = "edad", nullable = false)
    private Integer edad;

    @Column(name = "sexo", nullable = false)
    private String sexo;

    @Column(name = "fech_nac", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Define el formato de la fecha
    private Date fechNac;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "cedula_prof", nullable = false)
    private String cedula_prof;

    @Column(name = "telefono", nullable = true)
    private String telefono;

    @Column(name = "correo", nullable = true)
    private String correo;
}
