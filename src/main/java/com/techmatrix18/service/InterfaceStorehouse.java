package com.techmatrix18.service;

import com.techmatrix18.model.Storehouse;
import java.util.List;

public interface InterfaceStorehouse {
    List<Storehouse> getAllStorehouses();
    Storehouse getStorehouseById(Long id);

    boolean addStorehouse(Storehouse storehouse);
    boolean updateStorehouse(Storehouse storehouse);
    boolean deleteStorehouse(Long id);
}

