package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StorehouseBarcoTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        StorehouseBarco storehouseBarco = new StorehouseBarco();

        // Test setter
        storehouseBarco.setId(1L);
        storehouseBarco.setStorehouseId(1L);
        storehouseBarco.setBarcoId(1L);
        storehouseBarco.setCreatedAt(timestamp);
        storehouseBarco.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, storehouseBarco.getId());
        Assertions.assertEquals(1L, storehouseBarco.getStorehouseId());
        Assertions.assertEquals(1L, storehouseBarco.getBarcoId());
        Assertions.assertEquals(timestamp, storehouseBarco.getCreatedAt());
        Assertions.assertEquals(timestamp, storehouseBarco.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        StorehouseBarco storehouseBarco1 = new StorehouseBarco();
        storehouseBarco1.setId(1L);
        storehouseBarco1.setStorehouseId(1L);
        storehouseBarco1.setBarcoId(1L);
        storehouseBarco1.setCreatedAt(timestamp);
        storehouseBarco1.setUpdatedAt(timestamp);

        StorehouseBarco storehouseBarco2 = new StorehouseBarco();
        storehouseBarco2.setId(1L);
        storehouseBarco2.setStorehouseId(1L);
        storehouseBarco2.setBarcoId(1L);
        storehouseBarco2.setCreatedAt(timestamp);
        storehouseBarco2.setUpdatedAt(timestamp);

        StorehouseBarco storehouseBarco3 = new StorehouseBarco();
        storehouseBarco3.setId(2L);
        storehouseBarco3.setStorehouseId(2L);
        storehouseBarco3.setBarcoId(2L);
        storehouseBarco3.setCreatedAt(timestamp);
        storehouseBarco3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(storehouseBarco1, storehouseBarco2);
        Assertions.assertNotEquals(storehouseBarco1, storehouseBarco3);

        // Test equals hashCode()
        Assertions.assertEquals(storehouseBarco1.hashCode(), storehouseBarco2.hashCode());
        Assertions.assertNotEquals(storehouseBarco1.hashCode(), storehouseBarco3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        StorehouseBarco storehouseBarco = new StorehouseBarco();
        storehouseBarco.setId(1L);
        storehouseBarco.setStorehouseId(1L);
        storehouseBarco.setBarcoId(1L);
        storehouseBarco.setCreatedAt(timestamp);
        storehouseBarco.setUpdatedAt(timestamp);

        String ExpectedToString = "StorehouseBarco{" +
                "id=1" +
                ", storehouseId=1" +
                ", barcoId=1" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedToString, storehouseBarco.toString());
    }
}

