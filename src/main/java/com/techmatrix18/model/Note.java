package com.techmatrix18.model;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * Simple JavaBean domain that represents an Note
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 16-09-2024
 */

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String note;
    private Long userId;

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Note note1)) return false;
        return getId().equals(note1.getId()) && getTitle().equals(note1.getTitle()) && getNote().equals(note1.getNote()) && getUserId().equals(note1.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getNote(), getUserId());
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", userId=" + userId +
                '}';
    }
}

