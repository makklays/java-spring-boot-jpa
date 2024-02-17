package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class CityTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        City city = new City();

        // Test setter
        city.setId(1L);
        city.setTitle("Valencia");
        city.setDescription("This is Valencia");
        city.setCreatedAt(timestamp);
        city.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals("1L", city.getId());
        Assertions.assertEquals("Valencia", city.getTitle());
        Assertions.assertEquals("This is Valencia", city.getDescription());
        Assertions.assertEquals(timestamp, city.getCreatedAt());
        Assertions.assertEquals(timestamp, city.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        City city1 = new City();
        city1.setId(1L);
        city1.setTitle("Valencia");
        city1.setDescription("This is Valencia");
        city1.setCreatedAt(timestamp);
        city1.setUpdatedAt(timestamp);

        City city2 = new City();
        city2.setId(1L);
        city2.setTitle("Valencia");
        city2.setDescription("This is Valencia");
        city2.setCreatedAt(timestamp);
        city2.setUpdatedAt(timestamp);

        City city3 = new City();
        city3.setId(2L);
        city3.setTitle("Malaga");
        city3.setDescription("This is Malaga");
        city3.setCreatedAt(timestamp);
        city3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(city1, city2);
        Assertions.assertNotEquals(city1, city3);

        // Test equals hashCode()
        Assertions.assertEquals(city1.hashCode(), city2.hashCode());
        Assertions.assertNotEquals(city1.hashCode(), city3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        City city = new City();
        city.setId(1L);
        city.setTitle("Valencia");
        city.setDescription("This is Valencia");
        city.setCreatedAt(timestamp);
        city.setUpdatedAt(timestamp);

        String ExpectedToString = "City{" +
                "id=1L" +
                ", title='Valencia'" +
                ", description='This is Valencia'" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, city.toString());
    }
}

