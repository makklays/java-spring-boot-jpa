package com.techmatrix18.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a CardTransaction
 * from REST API NBU - Nation Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 08-12-2024
 */

@Entity
@Table(name="card_transactions")
public class CardTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number", length = 16)
    private Long number;

    @Column(name = "number_to", length = 255) // `card number` or `invoice`
    private String numberTo;

    @Column(name = "purpose", length = 255)
    private String purpose;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "is_add")
    private Boolean isAdd; // 0 or 1

    @Column(name = "is_withdrawal")
    private Boolean isWithdrawal; // 0 or 1

    @Column(name = "is_transfer")
    private Boolean isTransfer; // 0 or 1

    @Column(name = "is_payment")
    private Boolean isPayment; // 0 or 1

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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getNumberTo() {
        return numberTo;
    }

    public void setNumberTo(String numberTo) {
        this.numberTo = numberTo;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getIsAdd() {
        return isAdd;
    }

    public void setIsAdd(Boolean add) {
        isAdd = add;
    }

    public Boolean getIsWithdrawal() {
        return isWithdrawal;
    }

    public void setIsWithdrawal(Boolean withdrawal) {
        isWithdrawal = withdrawal;
    }

    public Boolean getIsTransfer() {
        return isTransfer;
    }

    public void setIsTransfer(Boolean transfer) {
        isTransfer = transfer;
    }

    public Boolean getIsPayment() {
        return isPayment;
    }

    public void setIsPayment(Boolean payment) {
        isPayment = payment;
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
        if (!(o instanceof CardTransaction that)) return false;
        return getId().equals(that.getId()) && getNumber().equals(that.getNumber()) && getNumberTo().equals(that.getNumberTo()) && getPurpose().equals(that.getPurpose()) && getAmount().equals(that.getAmount()) && isAdd.equals(that.isAdd) && isWithdrawal.equals(that.isWithdrawal) && isTransfer.equals(that.isTransfer) && isPayment.equals(that.isPayment) && getUser().equals(that.getUser()) && getCreatedAt().equals(that.getCreatedAt()) && getUpdatedAt().equals(that.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getNumberTo(), getPurpose(), getAmount(), isAdd, isWithdrawal, isTransfer, isPayment, getUser(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "CardTransaction{" +
                "id=" + id +
                ", number=" + number +
                ", numberTo='" + numberTo + '\'' +
                ", purpose='" + purpose + '\'' +
                ", amount=" + amount +
                ", isAdd=" + isAdd +
                ", isWithdrawal=" + isWithdrawal +
                ", isTransfer=" + isTransfer +
                ", isPayment=" + isPayment +
                ", user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

