package com.recipes.model;

import javax.persistence.Entity;

@Entity
public class Ingredient extends GenericEntity {
    private String name;
    private String measurement;
    private int quantity;

    public Ingredient() {
        super();
    }

    public Ingredient(String name, String measurement, int quantity) {
        this();
        this.name = name;
        this.measurement = measurement;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
