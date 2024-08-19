package com.techmatrix18.web.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simple REST controller for Mytask
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/mytask")
public class MytaskController {

    /*private final MytaskService mytaskService;

    public MytaskController(MytaskService mytaskService) {
        this.mytaskService = mytaskService;
    }

    @GetMapping("/list")
    public List<User> getUsers() throws ValidationException {
        List<User> list = mytaskService.getAllTasks();
        if (list != null) {
            return list;
        } else {
            return null; // ? algun json
        }
    }*/
}

