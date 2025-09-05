package com.uade.tpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.uade.tpo.demo.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
}    