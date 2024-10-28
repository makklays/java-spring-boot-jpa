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

    @Column(name = "photo", length = 500)
    private String photo;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
    private List<BarcoUser> barcoUsers;

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

    public String getPhoto() { return photo; }

    public void setPhoto(String photo) { this.photo = photo; }

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
        if (!(o instanceof Barco barco)) return false;
        return getId().equals(barco.getId()) && getTitle().equals(barco.getTitle()) && getDescription().equals(barco.getDescription()) && getYear().equals(barco.getYear()) && getWeight().equals(barco.getWeight()) && getSpeedometer().equals(barco.getSpeedometer()) && getPhoto().equals(barco.getPhoto()) && user.equals(barco.user) && getCreatedAt().equals(barco.getCreatedAt()) && getUpdatedAt().equals(barco.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getYear(), getWeight(), getSpeedometer(), getPhoto(), user, getCreatedAt(), getUpdatedAt());
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
                ", photo=" + photo +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

