package com.techmatrix18.model;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

//import javax.persistence.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * Simple JavaBean domain that represents a Product
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 255)
    @NotBlank
    private String title;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "weight")
    private Integer weight;

    @Column(name = "is_dangerous")
    private Integer isDangerous; // 0 or 1

    @Column(name = "is_glass")
    private Integer isGlass; // 0 or 1

    //@Column(name = "category_id", insertable=false, updatable=false)
    //private Long categoryId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    //@JoinColumn(name = "product_id", insertable = false, updatable = false)
    private List<BarcoProduct> barcoProducts;

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

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getIsDangerous() {
        return isDangerous;
    }

    public void setIsDangerous(Integer isDangerous) {
        this.isDangerous = isDangerous;
    }

    public Integer getIsGlass() {
        return isGlass;
    }

    public void setIsGlass(Integer isGlass) {
        this.isGlass = isGlass;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
        if (!(o instanceof Product product)) return false;
        return getId().equals(product.getId()) && getTitle().equals(product.getTitle()) && getDescription().equals(product.getDescription()) && getWeight().equals(product.getWeight()) && getIsDangerous().equals(product.getIsDangerous()) && getIsGlass().equals(product.getIsGlass()) && getCreatedAt().equals(product.getCreatedAt()) && getUpdatedAt().equals(product.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getWeight(), getIsDangerous(), getIsGlass(), getCreatedAt(), getUpdatedAt());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", weight=" + weight +
                ", isDangerous=" + isDangerous +
                ", isGlass=" + isGlass +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}

