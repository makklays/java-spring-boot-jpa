package com.techmatrix18.web.views;

import com.techmatrix18.model.Invoice;
import com.techmatrix18.model.User;
import com.techmatrix18.service.InvoiceService;
import com.techmatrix18.service.TransportationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/invoices")
public class InvoiceViewsController {
    //
    private static final Logger logger = LoggerFactory.getLogger(com.techmatrix18.web.api.UserController.class);

    private final InvoiceService invoiceService;
    private final TransportationService transportationService;

    public InvoiceViewsController(InvoiceService invoiceService, TransportationService transportationService) {
        this.invoiceService = invoiceService;
        this.transportationService = transportationService;
    }

    @GetMapping(path = "/list")
    public String listInvoices(Model model) {
        model.addAttribute("invoices", invoiceService.getAllInvoices());

        return "invoices/list";
    }

    @GetMapping("/add")
    public String add(Model model, Invoice invoice) {
        model.addAttribute("invoice", invoice);
        model.addAttribute("transportations", transportationService.getAllTransportations());

        return "invoices/add";
    }

    @PostMapping("/add-post")
    public String addPost(Model model, @Valid Invoice invoice, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "invoices/add";
        }

        invoiceService.addInvoice(invoice);

        return "redirect:/invoices/list";
    }

    @GetMapping("/edit/{invoiceId}")
    public String edit(HttpServletRequest request, HttpServletResponse response, @PathVariable Long invoiceId, Model model) throws Exception {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice.isPresent()) {
            model.addAttribute("invoice", invoice.get());
            logger.info("Invoice found..");
        } else {
            model.addAttribute("invoice", null);
            logger.warn("Error! Invoice not found..");
        }

        model.addAttribute("invoices", invoiceService.getAllInvoices());
        model.addAttribute("transportations", transportationService.getAllTransportations());

        return "invoices/edit";
    }

    @PostMapping("/update/{id}")
    public String editPost(@PathVariable("id") long id, @Valid Invoice invoice, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            invoice.setId(id);
            return "invoices/edit";
        }

        invoiceService.updateInvoice(invoice);

        return "redirect:/invoices/list";
    }

    @GetMapping("/delete/{invoiceId}")
    public void delete(HttpServletRequest request, HttpServletResponse response, @PathVariable Long invoiceId) throws IOException {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(invoiceId);
        if (invoice.isPresent()) {
            invoiceService.deleteInvoice(invoiceId);
        }

        response.sendRedirect("/invoices/list");
    }

    @GetMapping("/{invoiceId}")
    public String view(Model model, @PathVariable String invoiceId) {
        Optional<Invoice> invoice = invoiceService.getInvoiceById(Long.parseLong(invoiceId));
        if (invoice.isPresent()) {
            model.addAttribute("invoice", invoice.get());
            logger.info("Invoice found..");
        } else {
            model.addAttribute("invoice", null);
            logger.info("Error! Invoice not found..");
        }

        return "invoices/view";
    }
}

