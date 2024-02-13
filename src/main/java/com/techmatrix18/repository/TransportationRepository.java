package com.techmatrix18.repository;

import com.techmatrix18.model.Transportation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportationRepository extends CrudRepository<Transportation, Long> {
    List<Transportation> findByID(Long id);
    List<Transportation> findByBarcoIdAndStorehouseId(Integer barcoId, Integer storehouseId);

    List<Transportation> findByDistance(Integer distance);
    List<Transportation> findByDistanceLess(Integer distance);
    List<Transportation> findByDistanceMore(Integer distance);

    Page<Transportation> findAll(Pageable pageable);
}

