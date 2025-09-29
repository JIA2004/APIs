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

    @Lob
    @Column(name = "imagen", columnDefinition = "LONGBLOB")
    private byte[] imagen;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVehiculo;

    private String marca;
    private String modelo;
    private String color;


    @Column(name = "numero_chasis", unique = true, nullable = false)
    private Integer numeroChasis;

    @Column(name = "numero_motor", unique = true, nullable = false)
    private Integer numeroMotor;

    @Column(name = "precio_base")
    private Float precioBase;

    @Column(name = "tipo_vehiculo")
    private String tipoVehiculo;

    private Boolean disponible;
}
