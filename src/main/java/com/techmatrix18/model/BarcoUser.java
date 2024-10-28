package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a BarcoUser
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 08-10-2024
 */

@Entity
@Table(name = "barco_user")
public class BarcoUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "barco_id", nullable = false)
    private Barco barco;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "description", length = 500)
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assign_user_id", referencedColumnName = "id")
    private User assignUserId;

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

    public Barco getBarco() { return barco; }

    public void setBarco(Barco barco) { this.barco = barco; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAssignUserId() {
        return assignUserId;
    }

    public void setAssignUserId(User assignUserId) {
        this.assignUserId = assignUserId;
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
        if (!(o instanceof BarcoUser barcoUser)) return false;
        return getId().equals(barcoUser.getId()) && barco.equals(barcoUser.barco) && getUser().equals(barcoUser.getUser()) && getDescription().equals(barcoUser.getDescription()) && getAssignUserId().equals(barcoUser.getAssignUserId()) && getCreatedAt().equals(barcoUser.getCreatedAt()) && getUpdatedAt().equals(barcoUser.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), barco, getUser(), getDescription(), getAssignUserId(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "BarcoUser{" +
                "id=" + id +
                ", barco=" + barco +
                ", user=" + user +
                ", description='" + description + '\'' +
                ", assignUserId=" + assignUserId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

