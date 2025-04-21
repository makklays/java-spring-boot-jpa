package com.techmatrix18.service;

import com.techmatrix18.model.CardTransaction;
import com.techmatrix18.model.User;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.CardTransaction}
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 08-12-2024
 */

@Service
public interface CardTransactionService {
    List<CardTransaction> getAllCardTransactions();

    Optional<CardTransaction> getCardTransactionById(Long id);

    boolean addCardTransaction(CardTransaction cardTransaction);

    boolean deleteCardTransaction(Long id);

    User getUserByCardTransactionId(Long cardTransactionId);

    Page<CardTransaction> findPaginated(int pageNo, int pageSize);
}
