package com.vehiculerentakar.web.model;

import jakarta.persistence.*;

//@JsonFilter("monFiltreDynamique")
@Entity
public class Vehicule {

    //Attributs
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "model")
    private String model;
    @Column(name = "brand")
    private String brand;
    @Column(name = "color")
    private String color;
    @Column(name = "kilometers")
    private int kilometer;
    @Column(name = "horse_power")
    private int horsePower;
    @Column(name = "cargo")
    private int cargo;
    @Column(name = "capacity")
    private int capacity;
    @Column(name = "displacement", nullable = true)
    private int displacement;
    @Column(name = "registration")
    private String registration;
    @Column(name = "is_available")
    private boolean isAvailable;



    public Vehicule(int id, String type, String model, String brand, String color, int kilometer, int horsePower, int cargo, int capacity, int displacement, String registration, boolean isAvailable) {
        this.id = id;
        this.type = type;
        this.model = model;
        this.brand = brand;
        this.color = color;
        this.kilometer = kilometer;
        this.horsePower = horsePower;
        this.cargo = cargo;
        this.capacity = capacity;
        this.displacement = displacement;
        this.registration = registration;
        this.isAvailable = isAvailable;
    }

    public Vehicule() {

    }


    //Getter/Setter//


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getKilometer() {
        return kilometer;
    }

    public void setKilometer(int kilometer) {
        this.kilometer = kilometer;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    public String toString() {
        return "Vehicle{" +
                "|| Id = " + id +
                "|| Modèle = " + model +
                "|| Marque = " + brand +
                "|| Couleur = " + color +
                "|| Nbre de kilomètres = " + kilometer +
                "|| Type = " + type +
                "|| Nbre de passager = " + capacity +
                '}';
    }
}
