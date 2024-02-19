package com.techmatrix18.service;

import com.techmatrix18.model.Transportation;
import java.util.List;

public interface InterfaceTransportation {
    List<Transportation> getAllTransportations();
    Transportation getTransportationById(Long id);

    boolean addTransportation(Transportation transportation);
    boolean updateTransportation(Transportation transportation);
    boolean deleteTransportation(Long id);
}
