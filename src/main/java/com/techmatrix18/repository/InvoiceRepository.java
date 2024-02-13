package com.techmatrix18.repository;

import com.techmatrix18.model.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
    List<Invoice> findByID(Long id);
    List<Invoice> findByTitle(String title);
    List<Invoice> findByTransportationId(Integer transportationId);
    List<Invoice> findByAmount(Float amount);
    List<Invoice> findByStatus(String status);

    Page<Invoice> findAll(Pageable pageable);
}

