package com.techmatrix18.repository;

import com.techmatrix18.model.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency getById(Long id);

    List<Currency> findByTitle(String title);

    Page<Currency> findAll(Pageable pageable);
}

