package com.uade.tpo.demo.controllers;

import com.uade.tpo.demo.entity.Vehiculo;
import com.uade.tpo.demo.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    
    @Autowired
    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<Vehiculo> createVehicle(@RequestBody Vehiculo vehicle) {
        try {
            Vehiculo savedVehicle = vehicleService.saveVehicle(vehicle);
            return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Vehiculo> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getVehicleById(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .map(ResponseEntity::ok)
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
            vehicle.setImagen(vehicleDetails.getImagen());

            if (vehicleDetails.getCategory() != null && vehicleDetails.getCategory().getId() != null) {
                vehicle.setCategory(vehicleDetails.getCategory());
            }

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

    // Subida de vehículo con imagen
    @PostMapping("/upload")
    public ResponseEntity<Vehiculo> createVehicleWithImage(
            @RequestParam String marca,
            @RequestParam String modelo,
            @RequestParam String color,
            @RequestParam Integer numeroChasis,
            @RequestParam Integer numeroMotor,
            @RequestParam Float precioBase,
            @RequestParam String tipoVehiculo,
            @RequestParam Boolean disponible,
            @RequestParam Long categoryId,
            @RequestParam MultipartFile imagen) {

        try {
            Vehiculo vehicle = Vehiculo.builder()
                    .marca(marca)
                    .modelo(modelo)
                    .color(color)
                    .numeroChasis(numeroChasis)
                    .numeroMotor(numeroMotor)
                    .precioBase(precioBase)
                    .tipoVehiculo(tipoVehiculo)
                    .disponible(disponible)
                    .imagen(imagen.getBytes())
                    .build();

            // Asignamos categoría
            vehicle.setCategory(new com.uade.tpo.demo.categories.Category());
            vehicle.getCategory().setId(categoryId);

            Vehiculo savedVehicle = vehicleService.saveVehicle(vehicle);
            return new ResponseEntity<>(savedVehicle, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/imagen")
    public ResponseEntity<String> getVehicleImage(@PathVariable Long id) {
        return vehicleService.getVehicleById(id)
                .filter(v -> v.getImagen() != null)
                .map(v -> ResponseEntity.ok(Base64.getEncoder().encodeToString(v.getImagen())))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/imagen")
    public ResponseEntity<Vehiculo> updateVehicleImage(
            @PathVariable Long id,
            @RequestParam MultipartFile imagen) {

        return vehicleService.getVehicleById(id).map(vehicle -> {
            try {
                vehicle.setImagen(imagen.getBytes());
                Vehiculo updatedVehicle = vehicleService.saveVehicle(vehicle);
                return ResponseEntity.ok(updatedVehicle);
            } catch (Exception e) {
                // 🔹 Aquí especificamos el tipo
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).<Vehiculo>build();
            }
        }).orElse(ResponseEntity.notFound().<Vehiculo>build());
    }


    @PostMapping("/{id}/uploadImage")
    public ResponseEntity<String> uploadImage(@PathVariable Long id,
                                            @RequestParam("image") MultipartFile file) {
        try {
            Optional<Vehiculo> optionalVehicle = vehicleService.getVehicleById(id);
            if (optionalVehicle.isPresent()) {
                Vehiculo vehicle = optionalVehicle.get();
                vehicle.setImagen(file.getBytes());
                vehicleService.saveVehicle(vehicle);
                return ResponseEntity.ok("Imagen subida correctamente.");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al subir la imagen: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<String> getImage(@PathVariable Long id) {
        Optional<Vehiculo> optionalVehicle = vehicleService.getVehicleById(id);
        if (optionalVehicle.isPresent()) {
            Vehiculo vehicle = optionalVehicle.get();
            byte[] imageBytes = vehicle.getImagen();
            if (imageBytes != null) {
                String base64Image = Base64.getEncoder().encodeToString(imageBytes);
                return ResponseEntity.ok(base64Image);
            } else {
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
