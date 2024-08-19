package com.techmatrix18.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a Barco
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "barcos")
public class Barco implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    @NotBlank
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "weight")
    private Integer weight; // kg

    @Column(name = "speedometer")
    private Integer speedometer; // km

    @ManyToOne
    @JsonIgnore
    private User user;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public Barco() {}
    public Barco(String title, String description, Integer year, Integer weight, Integer speedometer, Timestamp createdAt, Timestamp updatedAt) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSpeedometer() {
        return speedometer;
    }

    public void setSpeedometer(Integer speedometer) {
        this.speedometer = speedometer;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getuser() {
        return user;
    }

    public void setuser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Barco barco = (Barco) o;
        return Objects.equals(id, barco.id) && Objects.equals(title, barco.title) && Objects.equals(description, barco.description) && Objects.equals(year, barco.year) && Objects.equals(weight, barco.weight) && Objects.equals(speedometer, barco.speedometer)  && Objects.equals(user, barco.user) && Objects.equals(createdAt, barco.createdAt) && Objects.equals(updatedAt, barco.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(year);
        result = 31 * result + Objects.hashCode(weight);
        result = 31 * result + Objects.hashCode(speedometer);
        result = 31 * result + Objects.hashCode(user);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Barco{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", weight=" + weight +
                ", speedometer=" + speedometer +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

