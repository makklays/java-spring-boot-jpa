package com.techmatrix18.service;

import com.techmatrix18.model.BarcoUser;
import com.techmatrix18.model.User;

import java.util.List;
import java.util.Set;

/**
 * Service class for {@link com.techmatrix18.model.BarcoUser}
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 07-10-2024
 */

public interface BarcoUserService {
    List<BarcoUser> getAllBarcoUser();
    BarcoUser getBarcoUserById(Long id);
    Set<User> getUsersByBarcoId(Long barcoId);

    List<BarcoUser> getAllBarcoUserByBarcoId(Long barcoId);

    boolean addBarcoUser(BarcoUser barcoUser);
    boolean updateBarcoUser(BarcoUser barcoUser);
    boolean deleteBarcoUser(Long id);
}
