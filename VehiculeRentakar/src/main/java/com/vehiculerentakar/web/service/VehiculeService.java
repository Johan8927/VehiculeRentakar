package com.vehiculerentakar.web.service;


import com.vehiculerentakar.web.model.Order;
import com.vehiculerentakar.web.model.Vehicule;
import com.vehiculerentakar.web.repository.VehiculeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final RestTemplate restTemplate;

    @Value("${service.user.url}")
    private String userServiceUrl;

    @Value("${service.order.url}")
    private String orderServiceUrl;

    @Value("${service.license.url}")
    private String licenseServiceUrl;

    @Autowired
    public VehiculeService(VehiculeRepository vehiculeRepository, RestTemplate restTemplate) {
        this.vehiculeRepository = vehiculeRepository;
        this.restTemplate = restTemplate;
    }

    public List<Vehicule> getAllVehicules() {
        System.out.print("Fetching all Vehiculs ||");
        return vehiculeRepository.findAll();
    }

    public Vehicule getVehiculeById(int id) {
        Vehicule vehicule = vehiculeRepository.findById(id).orElse(null);
        if (vehicule == null) {
            System.out.println("Vehicle not found");
        }
        System.out.print("Fetching Vehicle by id ||");
        System.out.println("Vehicul with id: " + id + " found  ||");
        System.out.println(vehicule);
        return vehicule;
    }

    public Vehicule saveVehicule(Vehicule vehicule) {
        System.out.print("Vehicle " + vehicule.getBrand() + vehicule.getModel() + "with" + vehicule.getRegistration() + " created ||");
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule getVehiculeByRegistration(String registration) {
        System.out.print("Fetching Vehicle by registration ||");
        System.out.println("Vehicle with type: " + registration + " found  ||");
        return vehiculeRepository.findByRegistration(registration).orElse(null);
    }

    public List<Vehicule> findByType(String type) {
        System.out.print("Fetching Vehicle by type ||");
        System.out.println("Vehicle with type: " + type + " found  ||");
        return vehiculeRepository.findbyType(type);
    }

    public Vehicule updateVehiculeById(int id, Vehicule vehicule) {
        Vehicule vehiculeToUpdate = vehiculeRepository.findById(id).orElse(null);
        if (vehiculeToUpdate == null) {
            System.out.println("Vehicle not found");
        } else {
            System.out.print("Vehicle " + vehicule.getBrand() + vehicule.getRegistration() + " updated");
            return vehiculeRepository.save(vehicule);
        }
        return null;
    }

    public List<Vehicule> getByAvailability() {
        return vehiculeRepository.findByIsAvailable();
    }

    public void deleteVehicule(int id) {
        vehiculeRepository.deleteById(id);
    }

    public List<Vehicule> getAvailableVehicles(LocalDate startDate, LocalDate endDate) {
        List<Vehicule> allVehicles = vehiculeRepository.findAll();
        return allVehicles.stream()
                .filter(vehicle -> isVehicleAvailable(vehicle.getId(), startDate, endDate))
                .collect(Collectors.toList());
    }

    public boolean isVehicleAvailable(int vehiculeId, LocalDate startDate, LocalDate endDate) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(orderServiceUrl + "/vehicule/" + vehiculeId)
                    .build()
                    .toUriString();
            ResponseEntity<List<Order>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Order>>() {
                    }
            );

            List<Order> orders = response.getBody();
            if (orders == null) return true;

            return orders.stream().noneMatch(order ->
                    isDateOverlapping(
                            startDate,
                            endDate,
                            order.getStartDate(),
                            order.getEndDate()
                    )
            );
        } catch (Exception e) {
            System.err.println("Erreur lors de la vérification de disponibilité: " + e.getMessage());
            throw new RuntimeException("Impossible de vérifier la disponibilité du véhicule", e);
        }
    }

    private boolean isDateOverlapping(LocalDate start1, LocalDate end1, LocalDate start2, LocalDate end2) {
        return (start1.isBefore(end2) || start1.isEqual(end2))
                && (end1.isAfter(start2) || end1.isEqual(start2));
    }

}
