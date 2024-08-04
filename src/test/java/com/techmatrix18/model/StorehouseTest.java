package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class StorehouseTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Storehouse storehouse = new Storehouse();

        // Test setter
        storehouse.setId(1L);
        //storehouse.setCityId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setDescription("This is Storehouse1");
        storehouse.setCreatedAt(timestamp);
        storehouse.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, storehouse.getId());
        //Assertions.assertEquals(1L, storehouse.getCityId());
        Assertions.assertEquals("Storehouse 1", storehouse.getTitle());
        Assertions.assertEquals("This is Storehouse1", storehouse.getDescription());
        Assertions.assertEquals(timestamp, storehouse.getCreatedAt());
        Assertions.assertEquals(timestamp, storehouse.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Storehouse storehouse1 = new Storehouse();
        storehouse1.setId(1L);
        //storehouse1.setCityId(1L);
        storehouse1.setTitle("Storehouse 1");
        storehouse1.setDescription("This is Storehouse1");
        storehouse1.setCreatedAt(timestamp);
        storehouse1.setUpdatedAt(timestamp);

        Storehouse storehouse2 = new Storehouse();
        storehouse2.setId(1L);
        //storehouse2.setCityId(1L);
        storehouse2.setTitle("Storehouse 1");
        storehouse2.setDescription("This is Storehouse1");
        storehouse2.setCreatedAt(timestamp);
        storehouse2.setUpdatedAt(timestamp);

        Storehouse storehouse3 = new Storehouse();
        storehouse3.setId(2L);
        //storehouse3.setCityId(2L);
        storehouse3.setTitle("Storehouse 2");
        storehouse3.setDescription("This is Storehouse2");
        storehouse3.setCreatedAt(timestamp);
        storehouse3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(storehouse1, storehouse2);
        Assertions.assertNotEquals(storehouse1, storehouse3);

        // Test equals hashCode()
        Assertions.assertEquals(storehouse1.hashCode(), storehouse2.hashCode());
        Assertions.assertNotEquals(storehouse1.hashCode(), storehouse3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Storehouse storehouse = new Storehouse();
        storehouse.setId(1L);
        //storehouse.setCityId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setDescription("This is Storehouse1");
        storehouse.setCreatedAt(timestamp);
        storehouse.setUpdatedAt(timestamp);

        String ExpectedToString = "StoreHouse{" +
                "id=1" +
                //", cityId=1L" +
                ", title='Storehouse 1'" +
                ", description='This is Storehouse1'" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedToString, storehouse.toString());
    }
}

