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
@Table(name = "cita_disponibles")
public class CitaDisponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita_disp")
    private Long idCitaDisp;

    @Column(name = "fech_cita", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechCita;

    @Column(name = "hora_inicio")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaInicio;

    @Column(name = "hora_termino")
    @DateTimeFormat(pattern = "HH:mm:ss") // Define el formato de la hora
    private Time horaTermino;
}
