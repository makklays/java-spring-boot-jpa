package com.techmatrix18.web.api;

import com.techmatrix18.model.CardTransaction;
import com.techmatrix18.model.User;
import com.techmatrix18.service.CardTransactionService;
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
 * @since 08-12-2024
 */

@RestController
@RequestMapping("/api/v1/card-transactions")
public class CardTransactionController {
    private final CardTransactionService cardTransactionService;
    private final UserService userService;

    public CardTransactionController(CardTransactionService cardTransactionService, UserService userService) {
        this.cardTransactionService = cardTransactionService;
        this.userService = userService;
    }

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<CardTransaction> getCardTransactions() throws ValidationException {
        return cardTransactionService.getAllCardTransactions();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOne(@PathVariable String id) throws ValidationException {
        Long cardTransactionId = Long.parseLong(id);
        Optional<CardTransaction> cardTransaction = cardTransactionService.getCardTransactionById(cardTransactionId);
        if (cardTransaction.isPresent()) {
            return cardTransaction.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find cardTransaction with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<CardTransaction> cardTransactions = cardTransactionService.findPaginated(pNo, pSize);
        if (cardTransactions != null) {
            return cardTransactions;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find cardTransactions with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addCardTransaction(@RequestParam String number, @RequestParam String numberTo, @RequestParam String purpose, @RequestParam String amount, @RequestParam boolean is_add, @RequestParam boolean is_withdrawal, @RequestParam boolean is_transfer, @RequestParam boolean is_payment, @RequestParam String userId) {
        // user
        User user = userService.getUserById(Long.parseLong(userId));

        // cardTransaction
        CardTransaction c = new CardTransaction();
        c.setNumber(Long.parseLong(number));
        c.setNumberTo(numberTo);
        c.setPurpose(purpose);
        c.setAmount(new BigDecimal(amount));

        c.setIsAdd(is_add);
        c.setIsWithdrawal(is_withdrawal);
        c.setIsTransfer(is_transfer);
        c.setIsPayment(is_payment);

        c.setUser(user);
        cardTransactionService.addCardTransaction(c);

        return "{\"status\": \"success\", \"message\": \"CardTransaction saved successfully!\"}";
    }
}

