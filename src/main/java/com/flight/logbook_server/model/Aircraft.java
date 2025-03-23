package com.flight.logbook_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "aircraft")
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматично генериране на primary key
    private Long id;

    @Column(name = "make", nullable = false) // Името на производителя
    private String make;

    @Column(name = "model", nullable = false) // Модел на самолета
    private String model;

    @Column(name = "variant") // Вариант (може да бъде NULL)
    private String variant;

    @Column(name = "registration", unique = true, nullable = false) // Уникален регистрационен номер
    private String registration;

    // Getters и Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }
}
