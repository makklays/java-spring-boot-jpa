package com.techmatrix18.service.impl;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.Category;
import com.techmatrix18.repository.BarcoRepository;
import com.techmatrix18.service.BarcoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link BarcoService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class BarcoServiceImpl implements BarcoService {
    @Lazy
    @Autowired
    private BarcoRepository barcoRepository;

    @Override
    public List<Barco> getAllBarcos() {
        List<Barco> list = new ArrayList<>();
        barcoRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Barco getBarcoById(Long id) {
        return barcoRepository.findById(id).get();
    }

    @Override
    public boolean addBarco(Barco barco) {
        Barco b = barcoRepository.save(barco);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBarco(Barco barco) {
        Barco b = barcoRepository.save(barco);
        if (!b.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBarco(Long id) {
        Barco barco = barcoRepository.getById(id);
        if (!barco.getTitle().isEmpty()) {
            barcoRepository.delete(barco);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Page<Barco> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return barcoRepository.findAll(pageable);
    }
}

