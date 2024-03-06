package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "barco_products")
public class BarcoProduct {
    @Id
    /*@SequenceGenerator(
        name = "barco_product_seq",
        sequenceName = "barco_product_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "barco_product_seq"
    )*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "barco_id", insertable=false, updatable=false)
    private Long barcoId;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="barco_id")
    private Barco barco;

    @Column(name = "product_id", insertable=false, updatable=false)
    private Long productId;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name="product_id")
    private Product product;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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
        if (!(o instanceof BarcoProduct that)) return false;
        return getId().equals(that.getId()) && getBarcoId().equals(that.getBarcoId()) && getProductId().equals(that.getProductId()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBarcoId(), getProductId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "BarcoProduct{" +
                "id=" + id +
                ", barcoId=" + barcoId +
                ", productId=" + productId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

