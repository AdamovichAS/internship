package com.andersenlab.adamovichjr.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Car {
    private int id;
    private String model;
    private String color;
    boolean isDeleted;
    Timestamp createdAt;

    public Car() {
    }

    public Car(String model, String color,boolean isDeleted) {
        this.model = model;
        this.color = color;
        this.isDeleted = isDeleted;
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
