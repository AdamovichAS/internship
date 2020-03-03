package com.andersenlab.adamovichjr.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@MappedSuperclass
public class CarEntity {
    private int id;
    private String model;
    private String color;
    boolean isDeleted;
    Timestamp createdAt;

    public CarEntity() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "model",nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "color", nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Column(name = "is_deleted",nullable = false)
    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Column(name = "created_at",nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
