package com.techmatrix18.service;

import com.techmatrix18.model.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.techmatrix18.model.Position}
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

public interface PositionService {
    List<Position> getAllPositions();
    Optional<Position> getPositionById(Long id);

    boolean addPosition(Position position);
    boolean updatePosition(Position position);
    boolean deletePosition(Long id);

    Page<Position> findPaginated(int pageNo, int pageSize);
}

