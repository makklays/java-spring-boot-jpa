package com.techmatrix18.service.impl;

import com.techmatrix18.model.Barco;
import com.techmatrix18.model.BarcoUser;
import com.techmatrix18.model.User;
import com.techmatrix18.repository.BarcoRepository;
import com.techmatrix18.repository.BarcoUserRepository;
import com.techmatrix18.service.BarcoService;
import com.techmatrix18.service.BarcoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implementation of {@link BarcoUserService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class BarcoUserServiceImpl implements BarcoUserService {
    @Lazy
    @Autowired
    private BarcoUserRepository barcoUserRepository;

    @Override
    public List<BarcoUser> getAllBarcoUser() {
        List<BarcoUser> list = new ArrayList<>();
        barcoUserRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public BarcoUser getBarcoUserById(Long id) { return barcoUserRepository.findById(id).get(); }

    @Override
    public List<BarcoUser> getAllBarcoUserByBarcoId(Long barcoId) { return barcoUserRepository.findByBarcoId(barcoId); }

    @Override
    public Set<User> getUsersByBarcoId(Long barcoId) {
        Set<User> list = new HashSet<>();
        // TODO
        return null;
    }

    @Override
    public boolean addBarcoUser(BarcoUser barcoUser) {
        BarcoUser b = barcoUserRepository.save(barcoUser);
        if (!b.getUser().getFirstname().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateBarcoUser(BarcoUser barcoUser) {
        BarcoUser b = barcoUserRepository.save(barcoUser);
        if (!b.getUser().getFirstname().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBarcoUser(Long id) {
        BarcoUser barcoUser = barcoUserRepository.getById(id);
        if (!barcoUser.getUser().getFirstname().isEmpty()) {
            barcoUserRepository.delete(barcoUser);
            return true;
        } else {
            return false;
        }
    }
}

