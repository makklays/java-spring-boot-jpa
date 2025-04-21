package com.techmatrix18.repository;

import com.techmatrix18.model.CardTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CardTransactionRepository extends JpaRepository<CardTransaction, Long> {

    CardTransaction getById(Long id);

    List<CardTransaction> findByNumber(Long number);

    List<CardTransaction> findByNumberTo(String numberTo);

    Page<CardTransaction> findAll(Pageable pageable);
}

