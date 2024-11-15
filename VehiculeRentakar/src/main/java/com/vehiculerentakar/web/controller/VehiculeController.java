package com.vehiculerentakar.web.controller;


import com.vehiculerentakar.web.model.Vehicule;
import com.vehiculerentakar.web.model.Order;
import com.vehiculerentakar.web.model.User;
import com.vehiculerentakar.web.service.VehiculeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VehiculeController {

    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @GetMapping("/vehicules")
    public List<Vehicule> getAllVehicules(){
        return vehiculeService.getAllVehicules();
    }
    @GetMapping("/vehicules/{id}")
    public Vehicule getVehiculeById(@PathVariable int id){
        return vehiculeService.getVehiculeById(id);
    }
    @GetMapping("/vehicule/{registration}")
    public Vehicule getVehiculeByRegistration(@PathVariable String registration){
        return vehiculeService.getVehiculeByRegistration(registration);
    }
    @GetMapping("/vehicule/available")
    public List<Vehicule> getVehiculeAvailable(){
        return vehiculeService.getByAvailability();
    }
    @GetMapping("/vehicule/type/{type}")
    public List<Vehicule> getVehiculeByType(@PathVariable String type){
        return vehiculeService.findByType(type);
    }
    @PostMapping("/vehicules")
    public Vehicule createVehicule(@RequestBody Vehicule vehicule){
        return vehiculeService.saveVehicule(vehicule);
    }
    @PutMapping("/vehicules/{id}")
    public Vehicule updateVehiculeById(@PathVariable int id,@RequestBody Vehicule vehicule){
        return vehiculeService.updateVehiculeById(id, vehicule);
    }
    @DeleteMapping("/vehicule/{id}")
    public void deleteVehicule(@PathVariable int id){
        vehiculeService.deleteVehicule(id);
    }
    @GetMapping("/vehicules/available/{startDate},{endDate}")
    public List<Vehicule> getVehiculeByDate(@PathVariable LocalDate startDate, @PathVariable LocalDate endDate){
        return vehiculeService.getAvailableVehicles(startDate,endDate);
    }
}
