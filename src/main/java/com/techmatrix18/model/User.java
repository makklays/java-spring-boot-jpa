package com.techmatrix18.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.List;
import java.util.Set;

import jakarta.persistence.ManyToMany;

/**
 * Simple JavaBean domain that represents a User
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", length = 255)
    @NotBlank
    private String firstname;

    @Column(name = "lastname", length = 255)
    @NotBlank
    private String lastname;

    @Column(name = "email", unique = true, nullable = false, length = 200)
    @NotBlank
    private String email;

    @Column(name = "password", length = 255)
    @NotBlank
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private Set<Role> roles;

    @Column(name = "bio", length = 500)
    private String bio;

    @ManyToOne
    @JoinColumn(unique = true) //, nullable = false)
    private Position position;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL) // name="user_id", referencedColumnName="userId"
    //@JoinColumn(name = "user_id", insertable = false, updatable = false)
    private List<BarcoUser> barcoUsers;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public User() {}

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public @NotBlank String getPassword() {
        return password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(roles, user.roles) && Objects.equals(bio, user.bio) && Objects.equals(position, user.position) && Objects.equals(barcoUsers, user.barcoUsers) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(firstname);
        result = 31 * result + Objects.hashCode(lastname);
        result = 31 * result + Objects.hashCode(email);
        result = 31 * result + Objects.hashCode(password);
        result = 31 * result + Objects.hashCode(roles);
        result = 31 * result + Objects.hashCode(bio);
        result = 31 * result + Objects.hashCode(position);
        result = 31 * result + Objects.hashCode(barcoUsers);
        result = 31 * result + Objects.hashCode(createdAt);
        result = 31 * result + Objects.hashCode(updatedAt);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", bio='" + bio + '\'' +
                ", roles='" + roles + '\'' +
                ", position=" + position +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

