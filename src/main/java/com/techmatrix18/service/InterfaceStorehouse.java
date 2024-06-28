package com.techmatrix18.service;

import com.techmatrix18.model.Storehouse;
import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.Storehouse}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface InterfaceStorehouse {
    List<Storehouse> getAllStorehouses();
    Storehouse getStorehouseById(Long id);

    boolean addStorehouse(Storehouse storehouse);
    boolean updateStorehouse(Storehouse storehouse);
    boolean deleteStorehouse(Long id);
}

