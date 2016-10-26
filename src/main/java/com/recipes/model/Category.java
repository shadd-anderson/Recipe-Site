package com.recipes.model;

import javax.persistence.Entity;

@Entity
public class Category extends GenericEntity {
    private String name;

    public Category() {
        super();
    }

    public Category(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
