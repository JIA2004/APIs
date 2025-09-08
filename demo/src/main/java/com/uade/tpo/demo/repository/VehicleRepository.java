package com.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uade.tpo.demo.entity.Vehiculo;

public interface VehicleRepository extends JpaRepository<Vehiculo, Long> {
}    