package com.techmatrix18.service.impl;

import com.techmatrix18.model.Card;
import com.techmatrix18.model.CardTransaction;
import com.techmatrix18.model.User;
import com.techmatrix18.repository.CardRepository;
import com.techmatrix18.repository.CardTransactionRepository;
import com.techmatrix18.service.CardService;
import com.techmatrix18.service.CardTransactionService;
import com.techmatrix18.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private final CardRepository cardRepository;
    private final CardTransactionService cardTransactionService;

    public CardServiceImpl(CardRepository cardRepository, CardTransactionService cardTransactionService) {
        this.cardRepository = cardRepository;
        this.cardTransactionService = cardTransactionService;
    }

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
    public User getUserByNumber(Long number) {
        Optional<Card> card = cardRepository.findByNumber(number);

        return card.get().getUser();
    }

    @Override
    public Page<Card> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return cardRepository.findAll(pageable);
    }

    @Transactional(propagation= Propagation.REQUIRED, isolation= Isolation.SERIALIZABLE)
    public boolean sendMoneyFromCardToCard(String cardIdFrom, String cardNumberTo, BigDecimal addAmount, String purpose) {
        try {
            // 1st insert
            Optional<Card> cardFrom = cardRepository.findById(Long.parseLong(cardIdFrom));

            BigDecimal amountFrom = cardFrom.get().getAmount();
            if (amountFrom.compareTo(addAmount) < 0) {
                return false;
            }
            BigDecimal amountNew = amountFrom.subtract(addAmount);
            cardFrom.get().setAmount(amountNew);
            this.updateCard(cardFrom.get());

            // 2nd insert
            Optional<Card> cardTo = cardRepository.findByNumber(Long.parseLong(cardNumberTo));

            BigDecimal amountTo = cardTo.get().getAmount();
            BigDecimal addAmountNew = amountTo.add(addAmount);
            cardTo.get().setAmount(addAmountNew);
            this.updateCard(cardTo.get());

            // 3rd insert
            CardTransaction cardTransaction = new CardTransaction();
            cardTransaction.setNumber(cardFrom.get().getNumber());
            cardTransaction.setNumberTo(cardTo.get().getNumber().toString());
            cardTransaction.setAmount(addAmount);
            cardTransaction.setPurpose(purpose);
            cardTransaction.setIsTransfer(true);
            cardTransaction.setIsAdd(false);
            cardTransaction.setIsWithdrawal(false);
            cardTransaction.setIsPayment(false);
            User user = cardFrom.get().getUser();
            cardTransaction.setUser(user);
            cardTransactionService.addCardTransaction(cardTransaction);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        } finally {
            System.out.println("Block finally - sendMoneyFromCardToCard();");
            return false;
        }
    }
}

