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
    private Boolean isDangerous; // 0 or 1

    @Column(name = "is_glass")
    private Boolean isGlass; // 0 or 1

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

    public Boolean getIsDangerous() {
        return isDangerous;
    }

    public void setIsDangerous(boolean isDangerous) {
        this.isDangerous = isDangerous;
    }

    public Boolean getIsGlass() {
        return isGlass;
    }

    public void setIsGlass(boolean isGlass) {
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
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (isDangerous != product.isDangerous) return false;
        if (isGlass != product.isGlass) return false;
        if (!Objects.equals(id, product.id)) return false;
        if (!Objects.equals(title, product.title)) return false;
        if (!Objects.equals(description, product.description)) return false;
        if (!Objects.equals(weight, product.weight)) return false;
        if (!Objects.equals(category, product.category)) return false;
        if (!Objects.equals(barcoProducts, product.barcoProducts))
            return false;
        if (!Objects.equals(createdAt, product.createdAt)) return false;
        return Objects.equals(updatedAt, product.updatedAt);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (isDangerous ? 1 : 0);
        result = 31 * result + (isGlass ? 1 : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (barcoProducts != null ? barcoProducts.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
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

