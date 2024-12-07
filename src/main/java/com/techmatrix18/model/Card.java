package com.techmatrix18.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a Card
 * from REST API NBU - Nation Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 07-12-2024
 */

@Entity
@Table(name = "cards")
public class Card implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    @NotBlank
    private String title;

    @Column(name = "number", length = 16)
    @NotBlank
    private int number;

    @Column(name = "type", length = 255) // Visa or MasterCard
    @NotBlank
    private String type;

    @Column(name = "month", length = 2)
    @NotBlank
    private int month;

    @Column(name = "year", length = 4)
    @NotBlank
    private int year;

    @Column(name = "cvv", length = 3)
    @NotBlank
    private int cvv;

    @Column(name = "cc", length = 3)
    @NotBlank
    private String cc;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
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
        if (!(o instanceof Card card)) return false;
        return getNumber() == card.getNumber() && getMonth() == card.getMonth() && getYear() == card.getYear() && getCvv() == card.getCvv() && getId().equals(card.getId()) && getTitle().equals(card.getTitle()) && getType().equals(card.getType()) && getCc().equals(card.getCc()) && getAmount().equals(card.getAmount()) && getUser().equals(card.getUser()) && getCreatedAt().equals(card.getCreatedAt()) && getUpdatedAt().equals(card.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getNumber(), getType(), getMonth(), getYear(), getCvv(), getCc(), getAmount(), getUser(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                ", type='" + type + '\'' +
                ", month=" + month +
                ", year=" + year +
                ", cvv=" + cvv +
                ", cc='" + cc + '\'' +
                ", amount=" + amount +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

