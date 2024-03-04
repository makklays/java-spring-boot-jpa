package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BarcoUserTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        BarcoUser barcoUser = new BarcoUser();

        // Test setter
        barcoUser.setId(1L);
        barcoUser.setBarcoId(1L);
        barcoUser.setUserId(1L);
        barcoUser.setCreatedAt(timestamp);
        barcoUser.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, barcoUser.getId());
        Assertions.assertEquals(1L, barcoUser.getBarcoId());
        Assertions.assertEquals(1L, barcoUser.getUserId());
        Assertions.assertEquals(timestamp, barcoUser.getCreatedAt());
        Assertions.assertEquals(timestamp, barcoUser.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        BarcoUser barcoUser1 = new BarcoUser();
        barcoUser1.setId(1L);
        barcoUser1.setBarcoId(1L);
        barcoUser1.setUserId(1L);
        barcoUser1.setCreatedAt(timestamp);
        barcoUser1.setUpdatedAt(timestamp);

        BarcoUser barcoUser2 = new BarcoUser();
        barcoUser2.setId(1L);
        barcoUser2.setBarcoId(1L);
        barcoUser2.setUserId(1L);
        barcoUser2.setCreatedAt(timestamp);
        barcoUser2.setUpdatedAt(timestamp);

        BarcoUser barcoUser3 = new BarcoUser();
        barcoUser3.setId(2L);
        barcoUser3.setBarcoId(2L);
        barcoUser3.setUserId(2L);
        barcoUser3.setCreatedAt(timestamp);
        barcoUser3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(barcoUser1, barcoUser2);
        Assertions.assertNotEquals(barcoUser1, barcoUser3);

        // Test equals hashCode()
        Assertions.assertEquals(barcoUser1.hashCode(), barcoUser2.hashCode());
        Assertions.assertNotEquals(barcoUser1.hashCode(), barcoUser3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        BarcoUser barcoUser = new BarcoUser();
        barcoUser.setId(1L);
        barcoUser.setBarcoId(1L);
        barcoUser.setUserId(1L);
        barcoUser.setCreatedAt(timestamp);
        barcoUser.setUpdatedAt(timestamp);

        String ExpectedToString = "Barco{" +
                "id=1L" +
                ", barcoId=1L" +
                ", userId=1L" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, barcoUser.toString());
    }
}

