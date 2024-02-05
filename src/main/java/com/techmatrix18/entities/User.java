package com.techmatrix18.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long Id;

    @Column(name = "firstname", length = 255)
    private String firstname;

    @Column(name = "lastname", length = 255)
    private String lastname;

    @Column(name = "email", unique = true, nullable = false, length = 200)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    @Column(name = "bio", length = 500)
    private String bio;

    @Column(name = "position_id")
    private Long positionId;


    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}

