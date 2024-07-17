package com.techmatrix18.web.api;

import com.techmatrix18.model.Invoice;
import com.techmatrix18.repository.InvoiceRepository;
import com.techmatrix18.service.implementation.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Simple controller for Invoice
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private InvoiceServiceImpl invoiceService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Invoice> getInvoices() throws ValidationException {
        return invoiceService.getAllInvoices();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addInvoice (@RequestParam String title, @RequestParam String description, @RequestParam Long transportationId, @RequestParam Float amount, @RequestParam String status) {
        Invoice i = new Invoice();
        i.setTitle(title);
        i.setDescription(description);
        i.setTransportationId(transportationId);
        i.setAmount(amount);
        i.setStatus(status);
        invoiceService.addInvoice(i);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updateInvoice (@RequestParam Long invoiceId, @RequestParam String title, @RequestParam String description, @RequestParam Long transportationId, @RequestParam Float amount, @RequestParam String status) {
        Invoice i = invoiceService.getInvoiceById(invoiceId);
        if (i.getId() != null) {
            i.setTitle(title);
            i.setDescription(description);
            i.setTransportationId(transportationId);
            i.setAmount(amount);
            i.setStatus(status);
            invoiceService.updateInvoice(i);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{invoiceId:\\\\d+}")
    public @ResponseBody String deleteInvoice (@PathVariable Long invoiceId) {
        Invoice i = invoiceService.getInvoiceById(invoiceId);
        if (i.getId() != null) {
            invoiceService.deleteInvoice(invoiceId);
        }
        return "Deleted";
    }
}

