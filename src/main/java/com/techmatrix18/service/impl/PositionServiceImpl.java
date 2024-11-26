package com.techmatrix18.service.impl;

import com.techmatrix18.model.Position;
import com.techmatrix18.repository.PositionRepository;
import com.techmatrix18.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link PositionService} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class PositionServiceImpl implements PositionService {
    @Lazy
    @Autowired
    private PositionRepository positionRepository;

    @Override
    public List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();
        positionRepository.findAll().forEach(list::add);

        return list;
    }

    @Override
    public Optional<Position> getPositionById(Long id) {
        return positionRepository.findById(id);
    }

    @Override
    public boolean addPosition(Position position) {
        Position pos = positionRepository.save(position);
        if (!pos.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updatePosition(Position position) {
        Position pos = positionRepository.save(position);
        if (!pos.getTitle().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deletePosition(Long id) {
        Position position = positionRepository.getById(id);
        if (!position.getTitle().isEmpty()) {
            positionRepository.delete(position);
            return true;
        } else {
            return false;
        }
    }
}

