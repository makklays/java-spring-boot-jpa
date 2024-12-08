package com.techmatrix18.web.api;

import com.techmatrix18.model.Card;
import com.techmatrix18.model.User;
import com.techmatrix18.service.CardService;
import com.techmatrix18.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Simple rest-controller for Card
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 07-12-2024
 */

@RestController
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;
    private final UserService userService;

    public CardController(CardService cardService, UserService userService) {
        this.cardService = cardService;
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<Card> getCards() throws ValidationException {
        return cardService.getAllCards();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOne(@PathVariable String id) throws ValidationException {
        Long cardId = Long.parseLong(id);
        Optional<Card> card = cardService.getCardById(cardId);
        if (card.isPresent()) {
            return card.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find card with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<Card> cards = cardService.findPaginated(pNo, pSize);
        if (cards != null) {
            return cards;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find cards with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addCard (@RequestParam String title, @RequestParam String number, @RequestParam String cc, @RequestParam String month, @RequestParam String year, @RequestParam String cvv, @RequestParam String type, @RequestParam String amount, @RequestParam String userId) {
        // user
        User user = userService.getUserById(Long.parseLong(userId));

        // card
        Card c = new Card();
        c.setTitle(title);
        c.setNumber(Long.parseLong(number));
        c.setMonth(Integer.parseInt(month));
        c.setYear(Integer.parseInt(year));
        c.setCvv(Integer.parseInt(cvv));
        c.setType(type);
        c.setAmount(new BigDecimal(amount));
        c.setUser(user);
        c.setCc(cc);
        cardService.addCard(c);

        return "{\"status\": \"success\", \"message\": \"Card saved successfully!\"}";
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateCard (@RequestParam String cardId, @RequestParam String title, @RequestParam String number, @RequestParam String cc, @RequestParam String month, @RequestParam String year, @RequestParam String cvv, @RequestParam String type, @RequestParam String amount, @RequestParam String userId) {
        Optional<Card> c = cardService.getCardById(Long.parseLong(cardId));
        if (c.isPresent()) {

            // user
            User user = userService.getUserById(Long.parseLong(userId));

            // card
            c.get().setTitle(title);
            c.get().setNumber(Long.parseLong(number));
            c.get().setMonth(Integer.parseInt(month));
            c.get().setYear(Integer.parseInt(year));
            c.get().setCvv(Integer.parseInt(cvv));
            c.get().setType(type);
            c.get().setAmount(new BigDecimal(amount));
            c.get().setUser(user);
            c.get().setCc(cc);
            cardService.updateCard(c.get());

            return "{\"status\": \"success\", \"message\": \"Card updated successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find card with ID=" + cardId + "\"}";
        }
    }
}

