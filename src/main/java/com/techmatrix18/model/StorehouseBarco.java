package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "storehouse_barcos")
public class StorehouseBarco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "storehouse_id", insertable=false, updatable=false)
    private Long storehouseId;

    @ManyToOne
    @JoinColumn(name = "storehouse_id", nullable = false)
    private Storehouse storehouse;

    @Column(name = "barco_id", insertable=false, updatable=false)
    private Long barcoId;

    @ManyToOne
    @JoinColumn(name = "barco_id", nullable = false)
    private Barco barco;

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

    public Long getStorehouseId() {
        return storehouseId;
    }

    public void setStorehouseId(Long storehouseId) {
        this.storehouseId = storehouseId;
    }

    public Long getBarcoId() {
        return barcoId;
    }

    public void setBarcoId(Long barcoId) {
        this.barcoId = barcoId;
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
        if (!(o instanceof StorehouseBarco that)) return false;
        return getId().equals(that.getId()) && getStorehouseId().equals(that.getStorehouseId()) && getBarcoId().equals(that.getBarcoId()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStorehouseId(), getBarcoId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "StorehouseBarco{" +
                "id=" + id +
                ", storehouseId=" + storehouseId +
                ", barcoId=" + barcoId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

