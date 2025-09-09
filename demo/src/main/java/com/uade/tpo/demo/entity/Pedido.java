package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido") // nombre en snake_case
    private Long idPedido;

    @Column(name = "fechaDeCreacion", nullable = false)
    private LocalDateTime fechaDeCreacion;

    @Column(name = "costoTotal", nullable = false)
    private Double costoTotal;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false) // FK a users
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false) // FK a vehicles
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "idFormadePago", nullable = false) // FK a formas_de_pago
    @Enumerated(EnumType.STRING)
    private FormaDePago formaDePago;
}
