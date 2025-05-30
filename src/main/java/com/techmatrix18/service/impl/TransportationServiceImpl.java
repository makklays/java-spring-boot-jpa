package com.techmatrix18.service.impl;

import com.techmatrix18.model.Transportation;
import com.techmatrix18.repository.TransportationRepository;
import com.techmatrix18.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link TransportationService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class TransportationServiceImpl implements TransportationService {
    @Lazy
    @Autowired
    private TransportationRepository transportationRepository;

    @Override
    public List<Transportation> getAllTransportations() {
        List<Transportation> list = new ArrayList<>();
        transportationRepository.findAll().forEach(e -> list.add(e));

        return list;
    }

    @Override
    public Optional<Transportation> getTransportationById(Long transportationId) {
        return transportationRepository.findById(transportationId);
    }

    @Override
    public synchronized boolean addTransportation(Transportation transportation) {
        Transportation st = transportationRepository.save(transportation);
        if (!st.getBarco().getTitle().isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public synchronized boolean updateTransportation(Transportation transportation) {
        Transportation tra = transportationRepository.save(transportation);
        if (tra.getId() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTransportation(Long transportationId) {
        Transportation tra = transportationRepository.getById(transportationId);
        if (tra.getId() != null) {
            transportationRepository.delete(tra);
            return true;
        } else {
            return false;
        }
    }
}

