package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "carrito_vehiculo")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarritoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float valor;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    private Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;
}
