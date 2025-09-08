package com.uade.tpo.demo.service;

import java.util.List;
import java.util.Optional;

import com.uade.tpo.demo.entity.Vehicle;

public interface IVehicleService {
    public Vehicle saveVehicle(Vehicle vehicle);
    public List<Vehicle> getAllVehicles();
    public Optional<Vehicle> getVehicleById(Long id);
    public void deleteVehicle(Long id);
    
}
