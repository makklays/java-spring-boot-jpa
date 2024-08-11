package com.techmatrix18.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserTest {

    public static final LocalDateTime localDateTime = LocalDateTime.of(2024, 2, 16, 12, 0, 0);
    public static final Timestamp timestamp = Timestamp.valueOf(localDateTime);

    @Test
    public void testEqualsGettersAndSetters () {
        User user = new User();

        // test setters
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

        // test getters
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("First", user.getFirstname());
        Assertions.assertEquals("Last", user.getLastname());
        Assertions.assertEquals("f.last@hotmail.com", user.getEmail());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertEquals("This is my bio", user.getBio());
        //Assertions.assertEquals(1L, user.getPositionId());
        Assertions.assertEquals(timestamp, user.getCreatedAt());
        Assertions.assertEquals(timestamp, user.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        Position position = new Position();
        position.setId(1L);
        position.setTitle("Position 1");

        User user1 = new User();
        user1.setId(1L);
        user1.setFirstname("First");
        user1.setLastname("Last");
        user1.setEmail("f.last@hotmail.com");
        user1.setPassword("password");
        user1.setBio("This is my bio");
        //user1.setRoles("ROLE_USER");
        user1.setPosition(position);
        user1.setCreatedAt(timestamp);
        user1.setUpdatedAt(timestamp);

        User user2 = new User();
        user2.setId(1L);
        user2.setFirstname("First");
        user2.setLastname("Last");
        user2.setEmail("f.last@hotmail.com");
        user2.setPassword("password");
        user2.setBio("This is my bio");
        //user2.setRoles("ROLE_USER");
        user2.setPosition(position);
        user2.setCreatedAt(timestamp);
        user2.setUpdatedAt(timestamp);

        User user3 = new User();
        user3.setId(1L);
        user3.setFirstname("Name");
        user3.setLastname("Surname");
        user3.setEmail("name.surname@hot.com");
        user3.setPassword("2222222");
        user3.setBio("This is your bio");
        //user3.setRoles("ROLE_USER");
        user3.setPosition(position);
        user3.setCreatedAt(timestamp);
        user3.setUpdatedAt(timestamp);

        // Test equals
        Assertions.assertEquals(user1, user2);
        Assertions.assertNotEquals(user1, user3);

        // Test hashCode
        Assertions.assertEquals(user1.hashCode(), user2.hashCode());
        Assertions.assertNotEquals(user1.hashCode(), user3.hashCode());
    }

    @Test
    public void testEqualsToString() {
        Position position = new Position();
        position.setId(1L);
        position.setTitle("Position 1");

        User user = new User();
        user.setId(1L);
        user.setFirstname("First");
        user.setLastname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        //user.setRoles("ROLE_USER");
        user.setPosition(position);
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        String ExpectedString = "User{" +
                "Id=1" +
                ", firstname='First'" +
                ", lastname='Last'" +
                ", email='f.last@hotmail.com'" +
                ", password='password'" +
                ", bio='This is my bio'" +
                //", roles='ROLE_USER'" +
                ", position=Position{id=1, title='Position 1', description='null', createdAt=null, updatedAt=null}" +
                ", createdAt=2024-02-16 12:00:00.0" +
                ", updatedAt=2024-02-16 12:00:00.0" +
                "}";

        Assertions.assertEquals(ExpectedString, user.toString());
    }
}

