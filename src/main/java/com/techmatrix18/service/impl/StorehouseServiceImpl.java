package com.techmatrix18.service.impl;

import com.techmatrix18.model.Storehouse;
import com.techmatrix18.repository.StorehouseRepository;
import com.techmatrix18.service.StorehouseService;
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
 * Implementation of {@link StorehouseService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class StorehouseServiceImpl implements StorehouseService {
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
    public List<Storehouse> getStorehousesByCityId(Long id) {
        return storehouseRepository.findByCityId(id);
    }

    @Override
    public Optional<Storehouse> getStorehouseById(Long id) {
        return storehouseRepository.findById(id);
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

    @Override
    public Page<Storehouse> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return storehouseRepository.findAll(pageable);
    }
}

