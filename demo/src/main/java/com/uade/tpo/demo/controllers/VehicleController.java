package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.Vehiculo;
import com.uade.tpo.demo.service.VehicleService;

import io.jsonwebtoken.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehiculo> createVehicle(@RequestBody Vehiculo vehicle) {
        Vehiculo savedVehicle = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Vehiculo> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(vehicle -> ResponseEntity.ok(vehicle))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> updateVehicle(@PathVariable Long id, @RequestBody Vehiculo vehicleDetails) {
        return vehicleService.getVehicleById(id).map(vehicle -> {
            vehicle.setMarca(vehicleDetails.getMarca());
            vehicle.setModelo(vehicleDetails.getModelo());
            vehicle.setPrecioBase(vehicleDetails.getPrecioBase());
            vehicle.setColor(vehicleDetails.getColor());
            vehicle.setNumeroChasis(vehicleDetails.getNumeroChasis());
            vehicle.setNumeroMotor(vehicleDetails.getNumeroMotor());
            vehicle.setTipoVehiculo(vehicleDetails.getTipoVehiculo());
            vehicle.setDisponible(vehicleDetails.getDisponible());
            Vehiculo updatedVehicle = vehicleService.saveVehicle(vehicle);
            return ResponseEntity.ok(updatedVehicle);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
        if (vehicleService.getVehicleById(id).isPresent()) {
            vehicleService.deleteVehicle(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Vehiculo> createVehicleWithImage(
            @RequestParam("marca") String marca,
            @RequestParam("modelo") String modelo,
            @RequestParam("color") String color,
            @RequestParam("numeroChasis") Integer numeroChasis,
            @RequestParam("numeroMotor") Integer numeroMotor,
            @RequestParam("precioBase") Float precioBase,
            @RequestParam("tipoVehiculo") String tipoVehiculo,
            @RequestParam("disponible") Boolean disponible,
            @RequestParam("imagen") MultipartFile imagen) throws IOException, java.io.IOException {

        Vehiculo vehicle = Vehiculo.builder()
                .marca(marca)
                .modelo(modelo)
                .color(color)
                .numeroChasis(numeroChasis)
                .numeroMotor(numeroMotor)
                .precioBase(precioBase)
                .tipoVehiculo(tipoVehiculo)
                .disponible(disponible)
                .imagen(imagen.getBytes()) // guardamos el archivo en BLOB
                .build();

        Vehiculo savedVehicle = vehicleService.saveVehicle(vehicle);
        return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
    }


    @GetMapping("/{id}/imagen")
    public ResponseEntity<String> getVehicleImage(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .filter(v -> v.getImagen() != null)
                .map(v -> {
                    String base64Image = java.util.Base64.getEncoder().encodeToString(v.getImagen());
                    return ResponseEntity.ok(base64Image);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/imagen")
    public ResponseEntity<Vehiculo> updateVehicleImage(
            @PathVariable Long id,
            @RequestParam("imagenUrl") String imagenUrl) {

        return vehicleService.getVehicleById(id).map(vehicle -> {
            vehicle.setImagenUrl(imagenUrl);
            Vehiculo updatedVehicle = vehicleService.saveVehicle(vehicle);
            return ResponseEntity.ok(updatedVehicle);
        }).orElse(ResponseEntity.notFound().build());
    }


}