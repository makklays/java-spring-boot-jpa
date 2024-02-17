package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BarcoProductTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        BarcoProduct barcoProduct = new BarcoProduct();

        // test setter
        barcoProduct.setId(1L);
        barcoProduct.setBarcoId(1L);
        barcoProduct.setProductId(1L);
        barcoProduct.setCreatedAt(timestamp);
        barcoProduct.setUpdatedAt(timestamp);

        // test getter
        Assertions.assertEquals(1L, barcoProduct.getId());
        Assertions.assertEquals(1L, barcoProduct.getBarcoId());
        Assertions.assertEquals(1L, barcoProduct.getProductId());
        Assertions.assertEquals("2024-02-16 12:00:00.0000000", barcoProduct.getCreatedAt());
        Assertions.assertEquals("2024-02-16 12:00:00.0000000", barcoProduct.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        BarcoProduct barcoProduct1 = new BarcoProduct();
        barcoProduct1.setId(1L);
        barcoProduct1.setBarcoId(1L);
        barcoProduct1.setProductId(1L);
        barcoProduct1.setCreatedAt(timestamp);
        barcoProduct1.setUpdatedAt(timestamp);

        BarcoProduct barcoProduct2 = new BarcoProduct();
        barcoProduct2.setId(1L);
        barcoProduct2.setBarcoId(1L);
        barcoProduct2.setProductId(1L);
        barcoProduct2.setCreatedAt(timestamp);
        barcoProduct2.setUpdatedAt(timestamp);

        BarcoProduct barcoProduct3 = new BarcoProduct();
        barcoProduct3.setId(2L);
        barcoProduct3.setBarcoId(2L);
        barcoProduct3.setProductId(2L);
        barcoProduct3.setCreatedAt(timestamp);
        barcoProduct3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(barcoProduct1, barcoProduct2);
        Assertions.assertNotEquals(barcoProduct1, barcoProduct3);

        // Test equals hashCode()
        Assertions.assertEquals(barcoProduct1.hashCode(), barcoProduct2.hashCode());
        Assertions.assertNotEquals(barcoProduct1.hashCode(), barcoProduct3.hashCode());
    }

    @Test
    public void testEqualsToStrings() {
        BarcoProduct barcoProduct = new BarcoProduct();
        barcoProduct.setId(1L);
        barcoProduct.setBarcoId(1L);
        barcoProduct.setProductId(1L);
        barcoProduct.setCreatedAt(timestamp);
        barcoProduct.setUpdatedAt(timestamp);

        String ExpectedToString = "BarcoProduct{" +
                "id=1L" +
                ", barcoId=1L" +
                ", productId=1L" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, barcoProduct.toString());
    }
}

