package com.techmatrix18.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a BarcoUser
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "barco_users")
public class BarcoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@Column(name = "barco_id", insertable=false, updatable=false)
    private Long barcoId;*/

    @ManyToOne
    @JoinColumn(name="barco_id")
    private Barco barco;

    /*@Column(name = "user_id", insertable=false, updatable=false)
    private Long userId;*/

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

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

    public Barco getBarco() {
        return this.barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
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
        if (!(o instanceof BarcoUser barcoDriver)) return false;
        return getId().equals(barcoDriver.getId()) && getBarco().equals(barcoDriver.getBarco()) && getUser().equals(barcoDriver.getUser()) && getCreatedAt().equals(barcoDriver.getCreatedAt()) && getUpdatedAt().equals(barcoDriver.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBarco(), getUser(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "BarcoUser{" +
                "id=" + id +
                ", barco=" + barco +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

