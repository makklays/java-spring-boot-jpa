package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class BarcoTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Barco barco = new Barco();

        // Test setter
        barco.setId(1L);
        barco.setTitle("BestBarco");
        barco.setDescription("This is best and fastly Barco");
        barco.setYear(2022);
        barco.setWeight(21000);
        barco.setSpeedometer(250000);
        barco.setCreatedAt(timestamp);
        barco.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals("1L", barco.getId());
        Assertions.assertEquals("BestBarco", barco.getTitle());
        Assertions.assertEquals("This is best and fastly Barco", barco.getDescription());
        Assertions.assertEquals(2022, barco.getYear());
        Assertions.assertEquals(21000, barco.getWeight());
        Assertions.assertEquals(250000, barco.getSpeedometer());
        Assertions.assertEquals(timestamp, barco.getCreatedAt());
        Assertions.assertEquals(timestamp, barco.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Barco barco1 = new Barco();
        barco1.setId(1L);
        barco1.setTitle("BestBarco");
        barco1.setDescription("This is best and fastly Barco");
        barco1.setYear(2022);
        barco1.setWeight(21000);
        barco1.setSpeedometer(250000);
        barco1.setCreatedAt(timestamp);
        barco1.setUpdatedAt(timestamp);

        Barco barco2 = new Barco();
        barco2.setId(1L);
        barco2.setTitle("BestBarco");
        barco2.setDescription("This is best and fastly Barco");
        barco2.setYear(2022);
        barco2.setWeight(21000);
        barco2.setSpeedometer(250000);
        barco2.setCreatedAt(timestamp);
        barco2.setUpdatedAt(timestamp);

        Barco barco3 = new Barco();
        barco3.setId(2L);
        barco3.setTitle("My Best Barco");
        barco3.setDescription("This is description for barco");
        barco3.setYear(2000);
        barco3.setWeight(1000);
        barco3.setSpeedometer(200);
        barco3.setCreatedAt(timestamp);
        barco3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(barco1, barco2);
        Assertions.assertNotEquals(barco1, barco3);

        // Test equals hashCode()
        Assertions.assertEquals(barco1.hashCode(), barco2.hashCode());
        Assertions.assertNotEquals(barco1.hashCode(), barco3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("BestBarco");
        barco.setDescription("This is best and fastly Barco");
        barco.setYear(2022);
        barco.setWeight(21000);
        barco.setSpeedometer(250000);
        barco.setCreatedAt(timestamp);
        barco.setUpdatedAt(timestamp);

        String ExpectedToString = "Barco{" +
                "id=1L" +
                ", title='BestBarco'" +
                ", description='This is best and fastly Barco'" +
                ", year=2022" +
                ", weight=21000" +
                ", speedometer=250000" +
                ", createdAt=2024-02-16 12:00:00.0000000" +
                ", updatedAt=2024-02-16 12:00:00.0000000" +
                "}";

        Assertions.assertEquals(ExpectedToString, barco.toString());
    }
}

