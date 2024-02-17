package com.techmatrix18.repository;

import com.techmatrix18.model.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PositionRepository extends CrudRepository<Position, Long> {
    Position getById(Long id);
    List<Position> findByTitle(String title);

    Page<Position> findAll(Pageable pageable);
}

