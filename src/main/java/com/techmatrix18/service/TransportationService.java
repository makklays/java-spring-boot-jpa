package com.techmatrix18.service;

import com.techmatrix18.model.Transportation;
import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.Transportation}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface TransportationService {
    List<Transportation> getAllTransportations();
    Transportation getTransportationById(Long id);

    boolean addTransportation(Transportation transportation);
    boolean updateTransportation(Transportation transportation);
    boolean deleteTransportation(Long id);
}
