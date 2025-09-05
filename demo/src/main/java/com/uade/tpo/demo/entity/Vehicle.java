package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehiculo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    @Column(nullable = false, length = 50)
    private String marca;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 50)
    private String color;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroChasis;

    @Column(nullable = false, unique = true, length = 50)
    private String numeroMotor;

    @Column(nullable = false)
    private Double precioBase;

    @Column(nullable = false, length = 50)
    private String tipoVehiculo;

    @Column(nullable = false)
    private boolean disponible;
}
