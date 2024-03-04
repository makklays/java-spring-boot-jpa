package com.techmatrix18.repository;

import com.techmatrix18.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        /*User user = User.buider()
                .firstname("Firstname");

        userRepository.save(user);*/
    }

    @Test
    public void printAllUsers() {
        List<User> list = userRepository.findAll();

        System.out.println("List of users: " + list);
    }

    @Test
    public void printUsersByFirstname() {
        List<User> list = userRepository.findByFirstname("Shivam");

        System.out.println("List of users by firstname: " + list);
    }

    @Test
    public void printUsersByLastname() {
        List<User> list = userRepository.findByLastname("Hinvam");

        System.out.println("List of users by lastname: " + list);
    }

    @Test
    public void printUsersByLastnameAndFirstname() {
        List<User> list = userRepository.findByLastnameAndFirstname("Shivam", "Hinvam");

        System.out.println("List of users by lastname and firstname: " + list);
    }

    @Test
    public void printUsersByEmail() {
        List<User> list = userRepository.findByEmail("shivm@gmail.com");

        System.out.println("List of users by email: " + list);
    }

    @Test
    public void findAllPagination() {
        Pageable firstPageWithtreeRecords = PageRequest.of(0, 3);
        Pageable secondPageWithtreeRecords = PageRequest.of(1, 2);

        List<User> users = userRepository.findAll(firstPageWithtreeRecords).getContent();

        Long totalElements = userRepository.findAll(firstPageWithtreeRecords).getTotalElements();
        Integer totalPages = userRepository.findAll(firstPageWithtreeRecords).getTotalPages();

        System.out.println("totalElements: " + totalElements);
        System.out.println("totalPages: " + totalPages);
        System.out.println("List of users pagination: " + users);
    }

    @Test
    public void findAllSorting() {
        Pageable sortByTitle = PageRequest.of(
                0,
                2,
                Sort.by("title").descending().and(Sort.by("created_at").descending())
        );

        List<User> list = userRepository.findAll(sortByTitle).getContent();

        System.out.println("List of users (sorting): " + list);
    }
}

