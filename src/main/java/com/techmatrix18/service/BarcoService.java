package com.techmatrix18.service;

import com.techmatrix18.model.Barco;
import java.util.List;

/**
 * Service class for {@link com.techmatrix18.model.Barco}
 *
 * @author Alexander Kuziv
 * @version 1.0
 * @since 19-02-2024
 */

public interface BarcoService {
    List<Barco> getAllBarcos();
    Barco getBarcoById(Long id);

    boolean addBarco(Barco barco);
    boolean updateBarco(Barco barco);
    boolean deleteBarco(Long id);
}
