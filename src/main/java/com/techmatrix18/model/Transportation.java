package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "transportations")
public class Transportation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "barco_id")
    private Long barcoId;

    @ManyToOne
    @JoinColumn(name = "barco_id", nullable = false)
    private Barco barco;

    @Column(name = "storehouse_id")
    private Long storehouseId;

    @ManyToOne
    @JoinColumn(name = "storehouse_id", nullable = false)
    private Storehouse storehouse;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "transportation", cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_id", insertable = false, updatable = false)
    private List<Invoice> invoices;

    @Column(name = "distance")
    private Integer distance;  // km

    @Column(name = "weight")
    private Integer weight;  // kg

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

    public Long getBarcoId() {
        return barcoId;
    }

    public void setBarcoId(Long barcoId) {
        this.barcoId = barcoId;
    }

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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
        if (!(o instanceof Transportation that)) return false;
        return getId().equals(that.getId()) && getBarcoId().equals(that.getBarcoId()) && getStorehouseId().equals(that.getStorehouseId()) && getDistance().equals(that.getDistance()) && getWeight().equals(that.getWeight()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBarcoId(), getStorehouseId(), getDistance(), getWeight(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Transportation{" +
                "id=" + id +
                ", barcoId=" + barcoId +
                ", storehouseId=" + storehouseId +
                ", distance=" + distance +
                ", weight=" + weight +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

