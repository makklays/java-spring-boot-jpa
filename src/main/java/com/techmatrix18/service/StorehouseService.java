package com.techmatrix18.service;

import com.techmatrix18.model.Storehouse;
import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Storehouse}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface StorehouseService {
    List<Storehouse> getAllStorehouses();
    Optional<Storehouse> getStorehouseById(Long id);

    List<Storehouse> getStorehousesByCityId(Long id);

    boolean addStorehouse(Storehouse storehouse);
    boolean updateStorehouse(Storehouse storehouse);
    boolean deleteStorehouse(Long id);
}

