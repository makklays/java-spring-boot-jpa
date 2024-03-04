package com.techmatrix18.service;

import com.techmatrix18.model.Barco;
import java.util.List;

public interface InterfaceBarco {
    List<Barco> getAllBarcos();
    Barco getBarcoById(Long id);

    boolean addBarco(Barco barco);
    boolean updateBarco(Barco barco);
    boolean deleteBarco(Long id);
}
