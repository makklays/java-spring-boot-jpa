package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CategoryTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Category category = new Category();

        // Test setter
        category.setId(1L);
        category.setTitle("Category");
        category.setDescription("This is description category");
        category.setCreatedAt(timestamp);
        category.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals("1L", category.getId());
        Assertions.assertEquals("Category", category.getTitle());
        Assertions.assertEquals("This is description category", category.getDescription());
        Assertions.assertEquals(timestamp, category.getCreatedAt());
        Assertions.assertEquals(timestamp, category.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setTitle("Category 1");
        category1.setDescription("This is description category 1");
        category1.setCreatedAt(timestamp);
        category1.setUpdatedAt(timestamp);

        Category category2 = new Category();
        category2.setId(1L);
        category2.setTitle("Category 1");
        category2.setDescription("This is description category 1");
        category2.setCreatedAt(timestamp);
        category2.setUpdatedAt(timestamp);

        Category category3 = new Category();
        category3.setId(2L);
        category3.setTitle("Category 2");
        category3.setDescription("This is description category 2");
        category3.setCreatedAt(timestamp);
        category3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(category1, category2);
        Assertions.assertNotEquals(category1, category3);

        // Test equals hashCode()
        Assertions.assertEquals(category1.hashCode(), category2.hashCode());
        Assertions.assertNotEquals(category1.hashCode(), category3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Category");
        category.setDescription("This is description category");
        category.setCreatedAt(timestamp);
        category.setUpdatedAt(timestamp);

        String ExpectedToString = "Category{" +
                "id=1L" +
                ", title='Category'" +
                ", description='This is description category'" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, category.toString());
    }
}

