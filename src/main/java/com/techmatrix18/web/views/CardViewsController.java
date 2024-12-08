package com.techmatrix18.web.views;

import com.techmatrix18.model.Card;
import com.techmatrix18.model.City;
import com.techmatrix18.service.CardService;
import com.techmatrix18.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
    private final UserService userService;

    public CardViewsController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Card> list = cardService.getAllCards();
        model.addAttribute("cards", list);

        return "cards/list";
    }

    @GetMapping("/add")
    public String add(Model model, Card card) {
        model.addAttribute("card", card);
        model.addAttribute("users", userService.getAllUsers());

        return "cards/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Card card, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "cards/add";
        }
        cardService.addCard(card);

        return "redirect:/cards/list";
    }

    @GetMapping(path = "/edit/{cardId}")
    public String edit(Model model, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.warn("Error! Card not found..");
        }

        return "cards/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Card card, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            card.setId(id);
            return "cards/edit";
        }

        cardService.updateCard(card);

        return "redirect:/cards/list";
    }

    @GetMapping("/delete/{cardId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            cardService.deleteCard(cardId);
        }

        response.sendRedirect("/cards/list");
    }

    @GetMapping("/{cardId}")
    public String view(Model model, @PathVariable String cardId) {
        Optional<Card> card = cardService.getCardById(Long.parseLong(cardId));
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.info("Error! Card not found..");
        }

        model.addAttribute("user", cardService.getUserByCardId(Long.parseLong(cardId)));

        return "cards/view";
    }
}

