package com.techmatrix18.repository;

import com.techmatrix18.model.Card;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card getById(Long id);

    List<Card> findByTitle(String title);

    Optional<Card> findByNumber(Long number);

    Page<Card> findAll(Pageable pageable);
}

