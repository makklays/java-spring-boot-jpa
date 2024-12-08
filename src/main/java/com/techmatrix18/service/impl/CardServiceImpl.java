package com.techmatrix18.service.impl;

import com.techmatrix18.model.Card;
import com.techmatrix18.model.User;
import com.techmatrix18.repository.CardRepository;
import com.techmatrix18.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link CardService} interface.
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 07-12-2024
 */

@Service
public class CardServiceImpl implements CardService {
    @Lazy
    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<Card> getAllCards() {
        List<Card> list = new ArrayList<>();
        cardRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Optional<Card> getCardById(Long id) {
        return cardRepository.findById(id);
    }

    @Override
    public boolean addCard(Card card) {
        Card c = cardRepository.save(card);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCard(Card card) {
        Card c = cardRepository.save(card);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCard(Long id) {
        Card card = cardRepository.getById(id);
        if (!card.getTitle().isEmpty()) {
            cardRepository.delete(card);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserByCardId(Long cardId) {
        Card card = cardRepository.getById(cardId);

        return card.getUser();
    }

    @Override
    public Page<Card> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cardRepository.findAll(pageable);
    }
}

