package com.vehiculerentakar.web.repository;


import com.vehiculerentakar.web.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Integer> {
    @Query("Select v From Vehicle v where v.id=:id")
    Optional<Vehicule> findById(int id);

    @Query("Select v From Vehicle v where v.registration=:registration")
    Optional<Vehicule> findByRegistration(String registration);

    @Query("Select v From Vehicle v Where v.isAvailable = true")
    List<Vehicule> findByIsAvailable();

    @Query("Select v From Vehicle v where v.type=:type")
    List<Vehicule> findbyType(String type);

    @Modifying
    @Query("UPDATE Vehicle v SET v.kilometer = :kilometer WHERE v.id = :id")
    int updateKilometersById(@Param("id") int id, @Param("kilometer") int kilometer);

}

