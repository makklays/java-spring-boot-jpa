package com.techmatrix18.service.impl;

import com.techmatrix18.model.CardTransaction;
import com.techmatrix18.model.User;
import com.techmatrix18.repository.CardTransactionRepository;
import com.techmatrix18.service.CardTransactionService;
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
 * Implementation of {@link CardTransactionService} interface.
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 08-12-2024
 */

@Service
public class CardTransactionServiceImpl implements CardTransactionService {
    @Lazy
    @Autowired
    private CardTransactionRepository cardTransactionRepository;

    @Override
    public List<CardTransaction> getAllCardTransactions() {
        List<CardTransaction> list = new ArrayList<>();
        cardTransactionRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Optional<CardTransaction> getCardTransactionById(Long id) {
        return cardTransactionRepository.findById(id);
    }

    @Override
    public boolean addCardTransaction(CardTransaction cardTransaction) {
        CardTransaction c = cardTransactionRepository.save(cardTransaction);
        if (c.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCardTransaction(Long id) {
        CardTransaction cardTransaction = cardTransactionRepository.getById(id);
        if (cardTransaction.getId() != null) {
            cardTransactionRepository.delete(cardTransaction);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User getUserByCardTransactionId(Long cardTransactionId) {
        CardTransaction cardTransaction = cardTransactionRepository.getById(cardTransactionId);

        return cardTransaction.getUser();
    }

    @Override
    public Page<CardTransaction> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cardTransactionRepository.findAll(pageable);
    }
}

