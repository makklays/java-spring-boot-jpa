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
        user.setFirstname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        user.setPositionId(1L);
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        // test getters
        Assertions.assertEquals(1L, user.getId());
        Assertions.assertEquals("first", user.getFirstname());
        Assertions.assertEquals("last", user.getLastname());
        Assertions.assertEquals("f.last@hotmail.com", user.getEmail());
        Assertions.assertEquals("password", user.getPassword());
        Assertions.assertEquals("This is my bio", user.getBio());
        Assertions.assertEquals(1L, user.getPositionId());
        Assertions.assertEquals("2024-02-16 12:00:00.0000000", user.getCreatedAt());
        Assertions.assertEquals("2024-02-16 12:00:00.0000000", user.getUpdatedAt());
    }

    @Test
    public void testEqualsAndHashCode() {
        User user1 = new User();
        user1.setId(1L);
        user1.setFirstname("First");
        user1.setFirstname("Last");
        user1.setEmail("f.last@hotmail.com");
        user1.setPassword("password");
        user1.setBio("This is my bio");
        user1.setPositionId(1L);
        user1.setCreatedAt(timestamp);
        user1.setUpdatedAt(timestamp);

        User user2 = new User();
        user2.setId(1L);
        user2.setFirstname("First");
        user2.setFirstname("Last");
        user2.setEmail("f.last@hotmail.com");
        user2.setPassword("password");
        user2.setBio("This is my bio");
        user2.setPositionId(1L);
        user2.setCreatedAt(timestamp);
        user2.setUpdatedAt(timestamp);

        User user3 = new User();
        user3.setId(1L);
        user3.setFirstname("Name");
        user3.setFirstname("Surname");
        user3.setEmail("name.surname@hot.com");
        user3.setPassword("2222222");
        user3.setBio("This is your bio");
        user3.setPositionId(2L);
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
        User user = new User();
        user.setId(1L);
        user.setFirstname("First");
        user.setFirstname("Last");
        user.setEmail("f.last@hotmail.com");
        user.setPassword("password");
        user.setBio("This is my bio");
        user.setPositionId(1L);
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        String ExpectedString = "User{" +
                "Id=1L" +
                ", firstname='First'" +
                ", lastname='Last'" +
                ", email='f.last@hotmail.com'" +
                ", password='password'" +
                ", bio='This is my bio'" +
                ", positionId=1L" +
                ", createdAt='2024-02-16 12:00:00.0000000'" +
                ", updatedAt='2024-02-16 12:00:00.0000000'" +
                "}";

        Assertions.assertEquals(ExpectedString, user.toString());
    }
}

