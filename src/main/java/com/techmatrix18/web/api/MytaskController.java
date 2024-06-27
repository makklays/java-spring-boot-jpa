package com.techmatrix18.web.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple controller for Mytask
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Controller
@RequestMapping("/mytask")
public class MytaskController {

    /*private final MytaskService mytaskService;

    public MytaskController(MytaskService mytaskService) {
        this.mytaskService = mytaskService;
    }

    @GetMapping("/list")
    public String viewMytasks(Model model) {
        model.addAttribute("mytasks", mytaskService.getMytasks());
        return "mytask.index";
    }

    @PostMapping("/list")
    public String searchMytasks() {
        model.addAttribute("mytasks", mytaskService.getMytasks());
        return "mytask.index";
    }

    @PostMapping("/addBook")
    public RedirectView addBook(@ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        final RedirectView redirectView = new RedirectView("/book/addBook", true);
        Book savedBook = bookService.addBook(book);
        redirectAttributes.addFlashAttribute("savedBook", savedBook);
        redirectAttributes.addFlashAttribute("addBookSuccess", true);
        return redirectView;
    }*/
}

