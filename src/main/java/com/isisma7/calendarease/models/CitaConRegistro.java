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
@Table(name = "cita_con_registro")
public class CitaConRegistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita_cn_reg")
    private Long idCnReg;

    @Column(name = "fech_cita", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechCita;

    @Column(name = "hora_inicio")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaInicio;

    @Column(name = "hora_termino")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaTermino;

    // Se le asocia un paciente ya registrado
    @ManyToOne(targetEntity = Paciente.class)
    @JoinColumn(name = "paciente", nullable = false)
    private Paciente paciente;

    // Se le asocia un servicio para la cita
    @ManyToOne(targetEntity = Servicio.class)
    @JoinColumn(name = "servicio", nullable = false)
    private Servicio servicio;
}
