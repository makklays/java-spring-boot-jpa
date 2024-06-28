package com.techmatrix18.service.implementation;

import com.techmatrix18.model.Storehouse;
import com.techmatrix18.repository.StorehouseRepository;
import com.techmatrix18.service.InterfaceStorehouse;
import com.techmatrix18.service.InterfaceTransportation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link InterfaceStorehouse} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class StorehouseImpl implements InterfaceStorehouse {
    @Lazy
    @Autowired
    private StorehouseRepository storehouseRepository;

    @Override
    public List<Storehouse> getAllStorehouses() {
        List<Storehouse> list = new ArrayList<>();
        storehouseRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Storehouse getStorehouseById(Long id) {
        return storehouseRepository.getById(id);
    }

    @Override
    public boolean addStorehouse(Storehouse storehouse) {
        Storehouse st = storehouseRepository.save(storehouse);
        if (!st.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateStorehouse(Storehouse storehouse) {
        Storehouse st = storehouseRepository.save(storehouse);
        if (!st.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteStorehouse(Long id) {
        Storehouse storehouse = storehouseRepository.getById(id);
        if (!storehouse.getTitle().isEmpty()) {
            storehouseRepository.delete(storehouse);
            return true;
        } else {
            return false;
        }
    }
}

