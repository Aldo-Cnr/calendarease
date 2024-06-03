package com.isisma7.calendarease.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cita_sin_registro")
public class CitaSinRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita_sn_reg")
    private Long idSnReg;

    @Column(name = "nombres_paciente", nullable = false)
    private String nombresPaciente;

    @Column(name = "apellidos_paciente", nullable = false)
    private String apellidosPaciente;

    @Column(name = "edad_paciente", nullable = false)
    private Integer edad;

    @Column(name = "sexo_paciente", nullable = false)
    private String sexo;

    @Column(name = "fech_nac_paciente", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Define el formato de la hora
    private Date fechNac;

    @Column(name = "telefono_paciente", nullable = true)
    private String telefono;

    @Column(name = "correo_paciente", nullable = true)
    private String correo;

    @Column(name = "fech_cita", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechCita;

    @Column(name = "hora_inicio")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaInicio;

    @Column(name = "hora_termino")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaTermino;

    // Se le asocia un servicio para la cita
    @ManyToOne(targetEntity = Servicio.class)
    @JoinColumn(name = "servicio", nullable = false)
    private Servicio servicio;
}
