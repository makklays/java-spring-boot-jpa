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
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("BestBarco");
        barco.setDescription("This is best and fastly Barco");
        barco.setYear(2022);
        barco.setWeight(21000);
        barco.setSpeedometer(250000);
        barco.setCreatedAt(timestamp);
        barco.setUpdatedAt(timestamp);

        User user = new User();
        user.setId(1L);
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        //user.setPositionId(1L);
        //user.setRoles("ROLE_USER");
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        BarcoUser barcoUser = new BarcoUser();
        // Test setter
        barcoUser.setId(1L);
        barcoUser.setBarco(barco);
        barcoUser.setUser(user);
        barcoUser.setCreatedAt(timestamp);
        barcoUser.setUpdatedAt(timestamp);

        // Test getter
        Assertions.assertEquals(1L, barcoUser.getId());
        Assertions.assertEquals(barco, barcoUser.getBarco());
        Assertions.assertEquals(user, barcoUser.getUser());
        Assertions.assertEquals(timestamp, barcoUser.getCreatedAt());
        Assertions.assertEquals(timestamp, barcoUser.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("BestBarco");
        barco.setDescription("This is best and fastly Barco");
        barco.setYear(2022);
        barco.setWeight(21000);
        barco.setSpeedometer(250000);
        barco.setCreatedAt(timestamp);
        barco.setUpdatedAt(timestamp);

        User user = new User();
        user.setId(1L);
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        //user.setPositionId(1L);
        //user.setRoles("ROLE_USER");
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        BarcoUser barcoUser1 = new BarcoUser();
        barcoUser1.setId(1L);
        barcoUser1.setBarco(barco);
        barcoUser1.setUser(user);
        barcoUser1.setCreatedAt(timestamp);
        barcoUser1.setUpdatedAt(timestamp);

        BarcoUser barcoUser2 = new BarcoUser();
        barcoUser2.setId(1L);
        barcoUser2.setBarco(barco);
        barcoUser2.setUser(user);
        barcoUser2.setCreatedAt(timestamp);
        barcoUser2.setUpdatedAt(timestamp);

        BarcoUser barcoUser3 = new BarcoUser();
        barcoUser3.setId(2L);
        barcoUser3.setBarco(barco);
        barcoUser3.setUser(user);
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
        Barco barco = new Barco();
        barco.setId(1L);
        barco.setTitle("BestBarco");
        barco.setDescription("This is best and fastly Barco");
        barco.setYear(2022);
        barco.setWeight(21000);
        barco.setSpeedometer(250000);
        barco.setCreatedAt(timestamp);
        barco.setUpdatedAt(timestamp);

        User user = new User();
        user.setId(1L);
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        //user.setPositionId(1L);
        //user.setRoles("ROLE_USER");
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        BarcoUser barcoUser = new BarcoUser();
        barcoUser.setId(1L);
        barcoUser.setBarco(barco);
        barcoUser.setUser(user);
        barcoUser.setCreatedAt(timestamp);
        barcoUser.setUpdatedAt(timestamp);

        String ExpectedToString = "BarcoUser{" +
                "id=1" +
                ", barco=Barco{id=1, title='BestBarco', description='This is best and fastly Barco', year=2022, weight=21000, speedometer=250000, createdAt=2024-02-16 12:00:00.0, updatedAt=2024-02-16 12:00:00.0}" +
                ", user=User{Id=1, firstname='First', lastname='Last', email='f.last@hotmail.com', password='password', bio='This is my bio', roles='null', position=null, createdAt=2024-02-16 12:00:00.0, updatedAt=2024-02-16 12:00:00.0}" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedToString, barcoUser.toString());
    }
}

