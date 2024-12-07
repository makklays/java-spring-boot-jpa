package com.techmatrix18.web.views;

import com.techmatrix18.model.Card;
import com.techmatrix18.service.CardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * Simple controller for Card
 * from REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 07-12-2024
 */

@Controller
@RequestMapping("/cards")
public class CardViewsController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.CardController.class);

    private final CardService cardService;

    public CardViewsController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Card> list = cardService.getAllCards();
        model.addAttribute("cards", list);

        return "cards/list";
    }
}

