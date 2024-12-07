package com.techmatrix18.service;

import com.techmatrix18.model.Card;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Card}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface CardService {
    List<Card> getAllCards();

    Optional<Card> getCardById(Long id);

    boolean addCard(Card card);

    boolean updateCard(Card card);

    boolean deleteCard(Long id);

    Page<Card> findPaginated(int pageNo, int pageSize);
}

