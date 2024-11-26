package com.techmatrix18.service.impl;

import com.techmatrix18.model.Invoice;
import com.techmatrix18.repository.InvoiceRepository;
import com.techmatrix18.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link InvoiceService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Lazy
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> list = new ArrayList<>();
        invoiceRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Override
    public boolean addInvoice(Invoice invoice) {
        Invoice i = invoiceRepository.save(invoice);
        if (!i.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateInvoice(Invoice invoice) {
        Invoice i = invoiceRepository.save(invoice);
        if (!i.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteInvoice(Long id) {
        Invoice invoice = invoiceRepository.getById(id);
        if (!invoice.getTitle().isEmpty()) {
            invoiceRepository.delete(invoice);
            return true;
        } else {
            return false;
        }
    }
}

