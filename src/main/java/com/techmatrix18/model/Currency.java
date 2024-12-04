package com.techmatrix18.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a Currency
 * from REST API NBU - Nation Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 04-12-2024
 */

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "r030", length = 255)
    private Integer r030;

    @Column(name = "title", length = 255)
    @NotBlank
    private String title;

    @Column(name = "rate", length = 255)
    private Float rate;

    @Column(name = "cc", length = 255)
    @NotBlank
    private String cc;

    @Column(name = "exchangedate", length = 255)
    @NotBlank
    private String exchangedate;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getR030() {
        return r030;
    }

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Currency currency)) return false;
        return getId().equals(currency.getId()) && getR030().equals(currency.getR030()) && getTitle().equals(currency.getTitle()) && getRate().equals(currency.getRate()) && getCc().equals(currency.getCc()) && getExchangedate().equals(currency.getExchangedate()) && getCreatedAt().equals(currency.getCreatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getR030(), getTitle(), getRate(), getCc(), getExchangedate(), getCreatedAt());
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", r030=" + r030 +
                ", title=" + title +
                ", rate=" + rate +
                ", cc='" + cc + '\'' +
                ", exchangedate='" + exchangedate + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}

