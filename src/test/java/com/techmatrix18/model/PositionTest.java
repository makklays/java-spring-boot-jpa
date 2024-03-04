package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class PositionTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Position position = new Position();

        // Test setter
        position.setId(1L);
        position.setTitle("Driver");
        position.setDescription("This is driver");
        position.setCreatedAt(timestamp);
        position.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, position.getId());
        Assertions.assertEquals("Driver", position.getTitle());
        Assertions.assertEquals("This is driver", position.getDescription());
        Assertions.assertEquals(timestamp, position.getCreatedAt());
        Assertions.assertEquals(timestamp, position.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Position position1 = new Position();
        position1.setId(1L);
        position1.setTitle("Driver");
        position1.setDescription("This is driver");
        position1.setCreatedAt(timestamp);
        position1.setUpdatedAt(timestamp);

        Position position2 = new Position();
        position2.setId(1L);
        position2.setTitle("Driver");
        position2.setDescription("This is driver");
        position2.setCreatedAt(timestamp);
        position2.setUpdatedAt(timestamp);

        Position position3 = new Position();
        position3.setId(2L);
        position3.setTitle("Driver2");
        position3.setDescription("This is driver2");
        position3.setCreatedAt(timestamp);
        position3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(position1, position2);
        Assertions.assertNotEquals(position1, position3);

        // Test equals hashCode()
        Assertions.assertEquals(position1.hashCode(), position2.hashCode());
        Assertions.assertNotEquals(position1.hashCode(), position3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Position position = new Position();
        position.setId(1L);
        position.setTitle("Driver");
        position.setDescription("This is driver");
        position.setCreatedAt(timestamp);
        position.setUpdatedAt(timestamp);

        String ExpectedToString = "Driver{" +
                "id=1L" +
                ", title='Driver'" +
                ", description='This is driver'" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, position.toString());
    }
}

