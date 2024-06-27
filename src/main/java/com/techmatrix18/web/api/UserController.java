package com.techmatrix18.web.api;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import com.techmatrix18.service.implementation.PositionImpl;
import com.techmatrix18.service.implementation.UserImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.bind.ValidationException;
import java.util.*;

/************************************
 * Author: Alexander Kuziv
 *  Email: makklays@gmail.com
 *   Date: 19-02-2024
 ***********************************/

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    // log
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserImpl userService;
    @Autowired
    private PositionImpl positionService;

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
        return "list";
    }

    @GetMapping(path = "/list2")
    public ModelAndView viewUsers2() {
        ModelAndView mav = new ModelAndView("users-index");
        //mav.addObject("greeting", "GeeksForGeeks Welcomes you to Spring!");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    // ---- thymeleaf example -------
    @GetMapping("/web-user-list-paging")
    public ModelAndView listUserViewPaging() {
        return findPaginated(1);
    }
    @GetMapping("/web-user-list")
    public ModelAndView listUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }
    @GetMapping("/web-user-add")
    public ModelAndView createUserView() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add");
        mav.addObject("user", new User());
        mav.addObject("positions", positionService.getAllPositions());
        return mav;
    }
    @PostMapping("/web-user-add")
    public ModelAndView createUser(@Valid User user, BindingResult result) {
        ModelAndView mav = new ModelAndView();
        if(result.hasErrors()) {
            logger.info("Validation errors while submitting form.");
            mav.setViewName("add");
            mav.addObject("user", user);
            mav.addObject("positions", positionService.getAllPositions());
            return mav;
        }
        userService.addUser(user);
        mav.addObject("users", userService.getAllUsers());
        mav.setViewName("list");
        logger.info("Form submitted successfully.");
        return mav;
    }
    @GetMapping("/web-user-list-paging/page/{pageNo}")
    public ModelAndView findPaginated(@PathVariable(value = "pageNo") int pageNo) {
        int pageSize = 3;

        Page <User> page = userService.findPaginated(pageNo, pageSize);
        List <User> listUsers = page.getContent();

        /*model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "index";*/

        ModelAndView mav = new ModelAndView();
        mav.setViewName("list");
        //mav.addObject("users", userService.getAllUsers());
        mav.addObject("currentPage", pageNo);
        mav.addObject("totalPages", page.getTotalPages());
        mav.addObject("totalItems", page.getTotalElements());
        mav.addObject("users", listUsers);
        return mav;
    }
    // ----------- end --------------

    @GetMapping("/example")
    public String toExample(Model model) {
        model.addAttribute("example", "Garry");
        return "/users/example";
    }
    @PostMapping("/example/add")
    public String createUser(@RequestParam String firstname, @RequestParam String lastname) {
        System.out.printf("First name: %s; Last name: %s \n", firstname, lastname);
        return "redirect:/users";
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
    public @ResponseBody String addUser (@RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String positionId) {
        User u = new User();
        u.setFirstname(firstname);
        u.setLastname(lastname);
        u.setEmail(email);
        u.setPassword(password);
        //Long longPositionId = Long.parseLong(positionId);
        //u.setPositionId(longPositionId);

        System.out.println(u.toString());

        userService.addUser(u);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateUser (@RequestParam String userId, @RequestParam String firstname, @RequestParam String lastname, @RequestParam String email, @RequestParam String password, @RequestParam String positionId) {
        Long longUserId = Long.parseLong(userId);
        User u = userService.getUserById(longUserId);
        if (!u.getEmail().isEmpty()) {
            u.setFirstname(firstname);
            u.setLastname(lastname);
            u.setEmail(email);
            u.setPassword(password);
            //Long longPositionId = Long.parseLong(positionId);
            //u.setPositionId(longPositionId);
            userService.updateUser(u);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{userId}")
    public @ResponseBody String deleteUser (@PathVariable String userId) {
        Long longUserId = Long.parseLong(userId);
        User u = userService.getUserById(longUserId);

        System.out.println("userId =====> " + u.getId());

        if (u.getId() != null) {
            userService.deleteUser(longUserId);
        } else {
            return "Hasn't userId = " + userId;
        }
        return "Deleted";
    }
}

