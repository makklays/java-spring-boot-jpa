package com.techmatrix18.service;

import com.techmatrix18.model.Invoice;
import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Invoice}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface InvoiceService {
    List<Invoice> getAllInvoices();
    Optional<Invoice> getInvoiceById(Long id);

    boolean addInvoice(Invoice invoice);
    boolean updateInvoice(Invoice invoice);
    boolean deleteInvoice(Long id);
}

