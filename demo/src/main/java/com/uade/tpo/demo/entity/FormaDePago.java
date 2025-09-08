package com.uade.tpo.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "formas_pago")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormaDePago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormaDePago;

    private String nombre;
}
