package com.techmatrix18.service;

import com.techmatrix18.model.Invoice;
import java.util.List;

public interface InterfaceInvoice {
    List<Invoice> getAllInvoices();
    Invoice getInvoiceById(Long id);

    boolean addInvoice(Invoice invoice);
    boolean updateInvoice(Invoice invoice);
    boolean deleteInvoice(Long id);
}

