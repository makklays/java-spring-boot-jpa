package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.implementation.UserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/************************************
 * Author: Alexander Kuziv
 *  Email: makklays@gmail.com
 *   Date: 19-02-2024
 ***********************************/

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImpl userService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @RequestMapping(value = "/products.do", method = RequestMethod.GET)
    public String handleRequest() {
        return "products";
    }

    @GetMapping("/list")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = new ModelAndView("users-index");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @GetMapping(path = "/list1")
    public String list1(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users-index";
    }

    @GetMapping(path = "/list2")
    public ModelAndView viewUsers2() {
        ModelAndView mav = new ModelAndView("users-index");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    /*@PostMapping("/addBook")
    public RedirectView addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/book/addBook", true);
        Book savedBook = bookService.addBook(book);
        redirectAttributes.addFlashAttribute("savedBook", savedBook);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        return redirectView;
    } */
    /*@RequestMapping(method = RequestMethod.DELETE)
    public String deletePet(@PathVariable int ownerId, @PathVariable int petId) {
        this.clinic.deletePet(petId);
        return "redirect:/owners/" + ownerId;
    }*/

    @GetMapping(path = "/all")
    public List<User> getUsers() throws ValidationException {
        List<User> list = userService.getAllUsers();
        if (list != null) {
            return list;
        } else {
            return null; // ? algun json
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam Long positionId) {
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPositionId(positionId);
        userService.addUser(u);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateUser (@RequestParam Long userId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam Long positionId) {
        User u = userService.getUserById(userId);
        if (!u.getEmail().isEmpty()) {
            u.setFirstname(firstname);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setPositionId(positionId);
            userService.updateUser(u);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{userId:\\\\d+}")
    public @ResponseBody String deleteUser (@PathVariable Long userId) {
        User u = userService.getUserById(userId);
        if (!u.getEmail().isEmpty()) {
            userService.deleteUser(userId);
        }
        return "Deleted";
    }
}

