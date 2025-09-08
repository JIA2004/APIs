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
    private Long idPedido;

    @Column(nullable = false)
    private LocalDateTime fechaDeCreacion;

    @Column(nullable = false)
    private Double costoTotal;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private User cliente;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehicle vehiculo;

    @ManyToOne
    @JoinColumn(name = "idFormaDePago", nullable = false)
    private FormaDePago formaDePago;
}