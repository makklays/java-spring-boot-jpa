package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TransportationTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters() {
        Transportation transportation = new Transportation();

        // Test setter
        transportation.setId(1L);
        //transportation.setBarco(1L);
        //transportation.setStorehouse(1L);
        transportation.setDistance(340000);
        transportation.setWeight(150000);
        transportation.setCreatedAt(timestamp);
        transportation.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, transportation.getId());
        //Assertions.assertEquals(1L, transportation.getBarco());
        //Assertions.assertEquals(1L, transportation.getStorehouse());
        Assertions.assertEquals(340000, transportation.getDistance());
        Assertions.assertEquals(150000, transportation.getWeight());
        Assertions.assertEquals(timestamp, transportation.getCreatedAt());
        Assertions.assertEquals(timestamp, transportation.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("TUAPSE");
        barco.setYear(2022);
        barco.setSpeedometer(2000);

        City city = new City();
        city.setId(1L);
        city.setTitle("City 1");

        Storehouse storehouse = new Storehouse();
        storehouse.setId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setCity(city);

        Transportation transportation1 = new Transportation();
        transportation1.setId(1L);
        transportation1.setBarco(barco);
        transportation1.setStorehouse(storehouse);
        transportation1.setDistance(340000);
        transportation1.setWeight(150000);
        transportation1.setCreatedAt(timestamp);
        transportation1.setUpdatedAt(timestamp);

        Transportation transportation2 = new Transportation();
        transportation2.setId(1L);
        transportation2.setBarco(barco);
        transportation2.setStorehouse(storehouse);
        transportation2.setDistance(340000);
        transportation2.setWeight(150000);
        transportation2.setCreatedAt(timestamp);
        transportation2.setUpdatedAt(timestamp);

        Transportation transportation3 = new Transportation();
        transportation3.setId(1L);
        transportation3.setBarco(barco);
        transportation3.setStorehouse(storehouse);
        transportation3.setDistance(8800);
        transportation3.setWeight(7700);
        transportation3.setCreatedAt(timestamp);
        transportation3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(transportation1, transportation2);
        Assertions.assertNotEquals(transportation1, transportation3);

        // Test equals hashCode()
        Assertions.assertEquals(transportation1.hashCode(), transportation2.hashCode());
        Assertions.assertNotEquals(transportation1.hashCode(), transportation3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("TUAPSE");
        barco.setYear(2022);
        barco.setSpeedometer(2000);

        City city = new City();
        city.setId(1L);
        city.setTitle("City 1");

        Storehouse storehouse = new Storehouse();
        storehouse.setId(1L);
        storehouse.setTitle("Storehouse 1");
        storehouse.setCity(city);

        Transportation transportation = new Transportation();
        transportation.setId(1L);
        //transportation.setBarco(1L);
        //transportation.setStorehouse(1L);
        transportation.setDistance(340000);
        transportation.setWeight(150000);
        transportation.setCreatedAt(timestamp);
        transportation.setUpdatedAt(timestamp);

        String ExpectedToString = "Transportation{" +
                "id=1" +
                ", barco=null" +
                ", storehouse=null" +
                ", distance=340000" +
                ", weight=150000" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedToString, transportation.toString());
    }
}

