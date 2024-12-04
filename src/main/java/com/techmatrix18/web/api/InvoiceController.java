package com.techmatrix18.web.api;

import com.techmatrix18.model.Invoice;
import com.techmatrix18.model.Transportation;
import com.techmatrix18.service.InvoiceService;
import com.techmatrix18.service.TransportationService;
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
 * Simple controller for Invoice
 *
 * @author Alexander Kuziv
 * @version 1.1
 */

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final TransportationService transportationService;

    public InvoiceController(InvoiceService invoiceService, TransportationService transportationService) {
        this.invoiceService = invoiceService;
        this.transportationService = transportationService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Invoice> getInvoices() throws ValidationException {
        return invoiceService.getAllInvoices();
    }

    @GetMapping(path = "/{id}")
    public Object getOneInvoice(@PathVariable String id) throws ValidationException {
        Long invoiceId = Long.parseLong(id);
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice.isPresent()) {
            return invoice.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find invoice with ID=" + id + "\"}";
        }
    }

    @GetMapping(path = "/page", produces = "application/json;charset=UTF-8")
    public Object getPage(@RequestParam String pageNo, @RequestParam String pageSize) {
        int pNo = Integer.parseInt(pageNo);
        int pSize = Integer.parseInt(pageSize);

        Page<Invoice> invoices = invoiceService.findPaginated(pNo, pSize);
        if (invoices != null) {
            return invoices;
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find invoices with pageNo=" + pageNo + " and pageSize=" + pageSize + " \"}";
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addInvoice (@RequestParam String title, @RequestParam String description, @RequestParam Long transportationId, @RequestParam Float amount, @RequestParam String status) {
        Invoice i = new Invoice();
        i.setTitle(title);
        i.setDescription(description);
        Optional<Transportation> tr = transportationService.getTransportationById(transportationId);
        if (tr.isPresent()) {
            i.setTransportation(tr.get());
        }
        i.setAmount(amount);
        i.setStatus(status);
        if (invoiceService.addInvoice(i)) {
            return "{\"status\": \"success\", \"message\": \"Invoice added successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add new invoice\"}";
        }
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateInvoice (@RequestParam Long invoiceId, @RequestParam String title, @RequestParam String description, @RequestParam Long transportationId, @RequestParam Float amount, @RequestParam String status) {
        Optional<Invoice> i = invoiceService.getInvoiceById(invoiceId);
        if (i.isPresent()) {
            i.get().setTitle(title);
            i.get().setDescription(description);
            Optional<Transportation> tr = transportationService.getTransportationById(transportationId);
            if (tr.isPresent()) {
                i.get().setTransportation(tr.get());
            }
            i.get().setAmount(amount);
            i.get().setStatus(status);
            if (invoiceService.updateInvoice(i.get())) {
                return "{\"status\": \"success\", \"message\": \"Invoice updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update invoice\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find invoice with ID=" + invoiceId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{invoiceId}")
    public @ResponseBody String deleteInvoice (@PathVariable Long invoiceId) {
        Optional<Invoice> i = invoiceService.getInvoiceById(invoiceId);
        if (i.isPresent()) {
            if (invoiceService.deleteInvoice(invoiceId)) {
                return "{\"status\": \"success\", \"message\": \"Invoice deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete invoice\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find invoice with ID=" + invoiceId + "\"}";
        }
    }

    @GetMapping(path="/currencies-nbu/{date}")
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
            return response.body();
        } else {
            return "{\"status\": \"error\", \"message\": \"Don't have values\"}";
        }
    }
}

