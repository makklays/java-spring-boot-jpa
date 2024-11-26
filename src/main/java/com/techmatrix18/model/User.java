package com.techmatrix18.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple JavaBean domain that represents a User
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "users")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @JsonIgnoreProperties(value = { "users" }, allowSetters = true)
    private Set<Role> roles = new HashSet<>();

    @Column(name = "bio", length = 500)
    private String bio;

    @ManyToOne
    @JoinColumn(unique = true) //, nullable = false)
    private Position position;

    @OneToMany(mappedBy = "user")
    private List<Barco> barcos;

    @Column(name="is_blocked")
    private boolean isBlocked;

    @OneToOne(mappedBy = "user")
    private BarcoUser barcoUser;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Instant updatedAt;

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

    @Override
    public String getPassword() {
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

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void setBarcos(List<Barco> barcos) {
        this.barcos = barcos;
    }

    public boolean getIsBlocked() { return isBlocked; }

    public void setIsBlocked(boolean blocked) { isBlocked = blocked; }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles
                .stream()
                .map(Role::getAuthorities)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return isBlocked == user.isBlocked && getId().equals(user.getId()) && getFirstname().equals(user.getFirstname()) && getLastname().equals(user.getLastname()) && getEmail().equals(user.getEmail()) && getPassword().equals(user.getPassword()) && getRoles().equals(user.getRoles()) && getBio().equals(user.getBio()) && getPosition().equals(user.getPosition()) && getBarcos().equals(user.getBarcos()) && getCreatedAt().equals(user.getCreatedAt()) && getUpdatedAt().equals(user.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getPassword(), getRoles(), getBio(), getPosition(), getBarcos(), isBlocked, getCreatedAt(), getUpdatedAt());
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                ", bio='" + bio + '\'' +
                ", position=" + position +
                ", barcos=" + barcos +
                ", isBlocked=" + isBlocked +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

