package com.techmatrix18.web.views;

import com.techmatrix18.model.CardTransaction;
import com.techmatrix18.service.CardTransactionService;
import com.techmatrix18.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Controller
@RequestMapping("/card-transactions")
public class CardTransactionViewsController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.CardTransactionController.class);

    private final CardTransactionService cardTransactionService;
    private final UserService userService;

    public CardTransactionViewsController(CardTransactionService cardTransactionService, UserService userService) {
        this.cardTransactionService = cardTransactionService;
        this.userService = userService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<CardTransaction> list = cardTransactionService.getAllCardTransactions();
        model.addAttribute("cardTransactions", list);

        return "card-transactions/list";
    }
}

