package com.techmatrix18.service;

import com.techmatrix18.model.Currency;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Currency}
 * for REST API NBU - National Bank Ukraine
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 04-12-2024
 */

public interface CurrencyService {
    List<Currency> getAllCurrencies();

    Optional<Currency> getCurrencyById(Long id);

    boolean addCurrency(Currency currency);

    boolean updateCurrency(Currency currency);

    boolean deleteCurrency(Long id);

    Page<Currency> findPaginated(int pageNo, int pageSize);
}

