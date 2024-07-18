package com.techmatrix18.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple controller for Mytask
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Controller
@RequestMapping("/tasks")
public class WebMytaskController {

    /*private final MytaskService mytaskService;

    public MytaskController(MytaskService mytaskService) {
        this.mytaskService = mytaskService;
    }

    @GetMapping("/list")
    public String viewMytasks(Model model) {
        model.addAttribute("mytasks", mytaskService.getMytasks());
        return "mytask.index";
    }*/
}

