package com.techmatrix18.web.views;

import com.techmatrix18.model.Currency;
import com.techmatrix18.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

/**
 * Simple controller for Currency
 * from REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 04-12-2024
 */

@Controller
@RequestMapping("/currencies")
public class CurrencyViewsController implements WebMvcConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final CurrencyService currencyService;

    public CurrencyViewsController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/list")
    public String list(Model model) throws Exception {
        List<Currency> list = currencyService.getAllCurrencies();
        model.addAttribute("currencies", list);
        return "currencies/list";
    }
}

