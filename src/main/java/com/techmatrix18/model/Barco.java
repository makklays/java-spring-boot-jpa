package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "barcos")
public class Barco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "year")
    private Integer year;

    @Column(name = "weight")
    private Integer weight; // kg

    @Column(name = "speedometer")
    private Integer speedometer; // km

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
    @JoinColumn(name = "barco_id", insertable = false, updatable = false)
    private List<StorehouseBarco> storehouseBarcos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
    @JoinColumn(name = "barco_id", insertable = false, updatable = false)
    private List<BarcoProduct> barcoProducts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "barco", cascade = CascadeType.ALL)
    @JoinColumn(name = "barco_id", insertable = false, updatable = false)
    private List<BarcoUser> barcoUsers;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Barco barco)) return false;
        return getId().equals(barco.getId()) && getTitle().equals(barco.getTitle()) && getDescription().equals(barco.getDescription()) && getYear().equals(barco.getYear()) && getWeight().equals(barco.getWeight()) && getSpeedometer().equals(barco.getSpeedometer()) && getCreatedAt().equals(barco.getCreatedAt()) && getUpdatedAt().equals(barco.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getYear(), getWeight(), getSpeedometer(), getCreatedAt(), getUpdatedAt());
    }

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

