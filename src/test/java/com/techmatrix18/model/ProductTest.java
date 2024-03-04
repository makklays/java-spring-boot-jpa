package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ProductTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Product product = new Product();

        // Test setter
        product.setId(1L);
        product.setTitle("Product1");
        product.setDescription("This is product1");
        product.setCreatedAt(timestamp);
        product.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, product.getId());
        Assertions.assertEquals("Product1", product.getTitle());
        Assertions.assertEquals("This is product1", product.getDescription());
        Assertions.assertEquals(timestamp, product.getCreatedAt());
        Assertions.assertEquals(timestamp, product.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setTitle("Product1");
        product1.setDescription("This is product1");
        product1.setCreatedAt(timestamp);
        product1.setUpdatedAt(timestamp);

        Product product2 = new Product();
        product2.setId(1L);
        product2.setTitle("Product1");
        product2.setDescription("This is product1");
        product2.setCreatedAt(timestamp);
        product2.setUpdatedAt(timestamp);

        Product product3 = new Product();
        product3.setId(2L);
        product3.setTitle("Product2");
        product3.setDescription("This is product2");
        product3.setCreatedAt(timestamp);
        product3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(product1, product2);
        Assertions.assertNotEquals(product1, product3);

        // Test equals hashCode()
        Assertions.assertEquals(product1.hashCode(), product2.hashCode());
        Assertions.assertNotEquals(product1.hashCode(), product3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Product1");
        product.setDescription("This is product1");
        product.setCreatedAt(timestamp);
        product.setUpdatedAt(timestamp);

        String ExpectedToString = "Driver{" +
                "id=1L" +
                ", title='Product1'" +
                ", description='This is product1'" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, product.toString());
    }
}

