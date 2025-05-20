package com.OOP.rentalX.controller;

import com.OOP.rentalX.model.Vehicle;
import com.OOP.rentalX.service.VehicleService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService vehicleService = new VehicleService();

    @PostMapping("/add")
    public String addVehicle(@RequestParam("vehicleId") String vehicleId,
                             @RequestParam("model") String model,
                             @RequestParam("type") String type,
                             @RequestParam("available") boolean available,
                             @RequestParam("rentPrice") double rentPrice,
                             @RequestParam("driverId") String driverId,
                             @RequestParam("image") MultipartFile image) {
        Vehicle v = new Vehicle(vehicleId, model, type, available, rentPrice, null, driverId);
        vehicleService.addVehicle(v, image);
        return "Vehicle added with image.";
    }

    @GetMapping("/driver/{driverId}")
    public List<Vehicle> getByDriver(@PathVariable String driverId) {
        return vehicleService.getVehiclesByDriverId(driverId);
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicles(@RequestParam(defaultValue = "false") boolean sortByPrice) {
        return vehicleService.getAllVehicles(sortByPrice);
    }

    @PutMapping("/update/{id}")
    public String updateVehicle(@PathVariable String id,
                                @RequestParam("model") String model,
                                @RequestParam("type") String type,
                                @RequestParam("available") boolean available,
                                @RequestParam("rentPrice") double rentPrice,
                                @RequestParam(value = "image", required = false) MultipartFile image,
                                @RequestParam("imagePath") String imagePath,
                                @RequestParam("driverId") String driverId) {
        Vehicle updatedVehicle = new Vehicle(id, model, type, available, rentPrice, imagePath, driverId);
        vehicleService.updateVehicle(id, updatedVehicle, image);
        return "Vehicle updated.";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable String id) {
        vehicleService.deleteVehicle(id);
        return "Vehicle deleted.";
    }
}