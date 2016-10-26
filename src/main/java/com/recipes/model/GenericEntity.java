package com.recipes.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Version
    private Long version;

    public GenericEntity() {
        id = null;
    }
}
