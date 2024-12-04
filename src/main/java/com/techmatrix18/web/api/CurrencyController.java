package com.techmatrix18.web.api;

import com.techmatrix18.model.Currency;
import com.techmatrix18.service.CurrencyService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.ValidationException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

/**
 * Simple controller for Currency
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 04-12-2024
 */

@RestController
@RequestMapping("/api/v1/currencies")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping(path = "/all", produces = "application/json;charset=UTF-8")
    public List<Currency> getCurrencies() throws ValidationException {
        return currencyService.getAllCurrencies();
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Object getOne(@PathVariable String id) throws ValidationException {
        Long cityId = Long.parseLong(id);
        Optional<Currency> city = currencyService.getCurrencyById(cityId);
        if (city.isPresent()) {
            return city.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find currency with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<Currency> currencies = currencyService.findPaginated(pNo, pSize);
        if (currencies != null) {
            return currencies;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find currencies with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add", produces = "application/json;charset=UTF-8")
    public @ResponseBody String addCurrency (@RequestParam Integer r030, @RequestParam String title, @RequestParam Float rate, @RequestParam String cc, @RequestParam String exchangedate) {
        Currency c = new Currency();
        c.setR030(r030);
        c.setTitle(title);
        c.setRate(rate);
        c.setCc(cc);
        c.setExchangedate(exchangedate);
        currencyService.addCurrency(c);

        return "{\"status\": \"success\", \"message\": \"Currency saved successfully!\"}";
    }

    @PatchMapping(path = "/update", produces = "application/json;charset=UTF-8")
    public @ResponseBody String updateCurrency (@RequestParam Long currencyId, @RequestParam Integer r030, @RequestParam String title, @RequestParam Float rate, @RequestParam String cc, @RequestParam String exchangedate) {
        Optional<Currency> c = currencyService.getCurrencyById(currencyId);
        if (c.isPresent()) {
            c.get().setR030(r030);
            c.get().setTitle(title);
            c.get().setRate(rate);
            c.get().setCc(cc);
            c.get().setExchangedate(exchangedate);
            currencyService.updateCurrency(c.get());
            return "{\"status\": \"success\", \"message\": \"Currency updated successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find currency with ID=" + currencyId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{currencyId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deleteCity (@PathVariable Long currencyId) {
        Optional<Currency> c = currencyService.getCurrencyById(currencyId);
        if (c.isPresent()) {
            currencyService.deleteCurrency(currencyId);
            return "{\"status\": \"success\", \"message\": \"Currency deleted successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find currency with ID=" + currencyId + "\"}";
        }
    }

    @GetMapping(path="/nbu/{date}")
    public String getCurrencyFromNBU(@PathVariable String date) throws URISyntaxException, IOException, InterruptedException {

        // https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date=20241204&json

        HttpRequest request = HttpRequest.newBuilder( new URI("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?date="+date+"&json") ).build();
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());

        if (!response.body().isEmpty()) {

            // response.body()
            /*for(curr : response.body()) {
                Currency c = new Currency();
                c.setR030(curr.r030);
                c.setTitle(curr.title);
                c.setRate(curr.rate);
                c.setCc(curr.cc);
                c.setExchangedate(curr.exchangedate);
                currencyService.addCurrency(c);
            }*/

            return response.body();
        } else {
            return "{\"status\": \"error\", \"message\": \"Don't have values\"}";
        }
    }
}

