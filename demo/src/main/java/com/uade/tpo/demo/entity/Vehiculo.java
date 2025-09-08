package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehiculos")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    private String marca;
    private String modelo;
    private String color;

    @Column(unique = true, nullable = false)
    private Integer numeroChasis;

    @Column(unique = true, nullable = false)
    private Integer numeroMotor;

    private Float precioBase;

    private String tipoVehiculo;

    private Boolean disponible;
}
