package com.techmatrix18.web.views;

import com.techmatrix18.model.Card;
import com.techmatrix18.model.City;
import com.techmatrix18.service.CardService;
import com.techmatrix18.service.CardTransactionService;
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
import java.math.BigDecimal;
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
    private final CardTransactionService cardTransactionService;

    public CardViewsController(CardService cardService, UserService userService, CardTransactionService cardTransactionService) {
        this.cardService = cardService;
        this.userService = userService;
        this.cardTransactionService = cardTransactionService;
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

    /*********************** OPERATIONS *********************/
    @GetMapping(path = "/{cardId}/add-money")
    public String addMoney(Model model, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.warn("Error! Card not found..");
        }

        return "cards/add-money";
    }

    @PostMapping("/add-money-post")
    public String addMoneyPost(HttpServletRequest request, HttpServletResponse response) {

        String card_id = request.getParameter("card_id");
        String number = request.getParameter("number");
        String amount = request.getParameter("amount");

        logger.info("Add money --> Number: " + number + " amount: " + amount);

        // TODO: Add transaction

        return "redirect:/cards/" + card_id;
    }

    @GetMapping(path = "/{cardId}/transfer")
    public String transfer(Model model, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.warn("Error! Card not found..");
        }

        return "cards/transfer";
    }

    @PostMapping("/transfer-post")
    public String addTransferPost(HttpServletRequest request, HttpServletResponse response) {

        String card_id = request.getParameter("card_id");
        String numberTo = request.getParameter("numberTo");
        String amount = request.getParameter("amount");

        logger.info("Transfer --> NumberTo: " + numberTo + " amount: " + amount);

        // TODO: Add transaction

        return "redirect:/cards/" + card_id;
    }

    @GetMapping(path = "/{cardId}/withdrawal")
    public String withdrawal(Model model, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.warn("Error! Card not found..");
        }

        return "cards/withdrawal";
    }

    @PostMapping("/withdrawal-post")
    public String addWithdrawalPost(HttpServletRequest request, HttpServletResponse response) {

        String card_id = request.getParameter("card_id");
        String numberTo = request.getParameter("numberTo");
        String amount = request.getParameter("amount");

        logger.info("Withdrawal --> NumberTo: " + numberTo + " amount: " + amount);

        // TODO: Add transaction

        return "redirect:/cards/" + card_id;
    }

    @GetMapping(path = "/{cardId}/payment")
    public String payment(Model model, @PathVariable Long cardId) throws IOException {
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.get().getId() != null) {
            model.addAttribute("card", card.get());
            logger.info("Card found..");
        } else {
            model.addAttribute("card", null);
            logger.warn("Error! Card not found..");
        }

        return "cards/payment";
    }

    @PostMapping("/payment-post")
    public String addPaymentPost(HttpServletRequest request, HttpServletResponse response) {

        String cardId = request.getParameter("cardId");
        String numberTo = request.getParameter("numberTo");
        String amount = request.getParameter("amount");
        String purpose = request.getParameter("purpose");

        logger.info("Payment --> NumberTo: " + numberTo + " amount: " + amount);

        // Add transaction
        cardService.sendMoneyFromCardToCard(cardId, numberTo, BigDecimal.valueOf(Long.parseLong(amount)), purpose);

        return "redirect:/cards/" + cardId;
    }
}

