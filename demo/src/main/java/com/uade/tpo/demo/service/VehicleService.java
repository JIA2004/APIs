package com.uade.tpo.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;
import com.uade.tpo.demo.entity.Vehiculo;
import com.uade.tpo.demo.repository.VehicleRepository;


@Service
public class VehicleService implements IVehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehiculo saveVehicle(Vehiculo vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehiculo> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Optional<Vehiculo> getVehicleById(Long id) {
        return vehicleRepository.findById(id);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }
}