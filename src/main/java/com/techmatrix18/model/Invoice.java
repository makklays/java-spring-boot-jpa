package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a Invoice
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    private String title;

    @Column(name = "description", length = 500)
    private String description; // kg and km at description

    /*@Column(name = "transportation_id", insertable=false, updatable=false)
    private Long transportationId;*/

    @ManyToOne
    @JoinColumn(name="transportation_id")
    private Transportation transportation;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "status", length = 255)
    private String status; // paid, not_paid, canceled

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

    public Transportation getTransportation() {
        return this.transportation;
    }

    public void setTransportation(Transportation transportation) {
        this.transportation = transportation;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

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
        if (!(o instanceof Invoice invoice)) return false;
        return getId().equals(invoice.getId()) && getTitle().equals(invoice.getTitle()) && getDescription().equals(invoice.getDescription()) && getTransportation().equals(invoice.getTransportation()) && getAmount().equals(invoice.getAmount()) && getCreatedAt().equals(invoice.getCreatedAt()) && getUpdatedAt().equals(invoice.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getTransportation(), getAmount(), getStatus(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", transportation=" + transportation +
                ", amount=" + amount +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

