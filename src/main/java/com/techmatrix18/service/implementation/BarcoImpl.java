package com.techmatrix18.service.implementation;

import com.techmatrix18.model.Barco;
import com.techmatrix18.repository.BarcoRepository;
import com.techmatrix18.service.InterfaceBarco;
import com.techmatrix18.service.InterfacePosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link InterfaceBarco} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class BarcoImpl implements InterfaceBarco {
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
        return barcoRepository.getById(id);
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
}

