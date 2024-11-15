package com.vehiculerentakar.web.model;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private int licenceId;
    private int hasOrderId;

    public User(int id, String firstName, String lastName, int licenceId, int hasOrderId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.licenceId = licenceId;
        this.hasOrderId = hasOrderId;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLicenceId() {
        return licenceId;
    }

    public void setLicenceId(int licenceId) {
        this.licenceId = licenceId;
    }

    public int getHasOrderId() {
        return hasOrderId;
    }

    public void setHasOrderId(int hasOrderId) {
        this.hasOrderId = hasOrderId;
    }
}
