package com.isisma7.calendarease.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "expediente")
public class Expediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_expediente")
    private Long idExpediente;

    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @Column(name = "tratamiento", nullable = false)
    private String tratamiento;

    // Se le asocia una cita con registro que puede ser nula
    @ManyToOne(targetEntity = CitaConRegistro.class)
    @JoinColumn(name = "cita_cn_reg", nullable = true)
    private CitaConRegistro citaCnReg;

    // Se le asocia una cita sin registro que puede ser nula
    @ManyToOne(targetEntity = CitaSinRegistro.class)
    @JoinColumn(name = "cita_sn_reg", nullable = true)
    private CitaSinRegistro citaSnReg;

}
