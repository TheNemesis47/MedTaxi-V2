package com.medtaxi.backend.enums;

public enum OperationTypeEnum {
    CREATE("Create a new entity"),
    READ("Retrieve an entity"),
    UPDATE("Update an existing entity"),
    DELETE("Delete an entity");

    private final String description;

    OperationTypeEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}