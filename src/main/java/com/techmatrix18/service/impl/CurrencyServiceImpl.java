package com.techmatrix18.service.impl;

import com.techmatrix18.model.Currency;
import com.techmatrix18.repository.CurrencyRepository;
import com.techmatrix18.service.CurrencyService;
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
 * Implementation of {@link CurrencyService} interface.
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 04-12-2024
 */

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Lazy
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        List<Currency> list = new ArrayList<>();
        currencyRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Optional<Currency> getCurrencyById(Long id) {
        return currencyRepository.findById(id);
    }

    @Override
    public boolean addCurrency(Currency currency) {
        Currency c = currencyRepository.save(currency);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateCurrency(Currency currency) {
        Currency c = currencyRepository.save(currency);
        if (!c.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteCurrency(Long id) {
        Currency city = currencyRepository.getById(id);
        if (!city.getTitle().isEmpty()) {
            currencyRepository.delete(city);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Currency> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return currencyRepository.findAll(pageable);
    }
}

