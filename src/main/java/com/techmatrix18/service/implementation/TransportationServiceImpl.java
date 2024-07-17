package com.techmatrix18.service.implementation;

import com.techmatrix18.model.Transportation;
import com.techmatrix18.repository.TransportationRepository;
import com.techmatrix18.service.TransportationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

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
    public Transportation getTransportationById(Long transportationId) {
        return transportationRepository.getById(transportationId);
    }

    @Override
    public synchronized boolean addTransportation(Transportation transportation) {
        // check email
        Transportation tra = transportationRepository.getById(transportation.getId());
        if (tra.getId() != null) {
            return false;
        } else {
            // add
            transportationRepository.save(transportation);
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

