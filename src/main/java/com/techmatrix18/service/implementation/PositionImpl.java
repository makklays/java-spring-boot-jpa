package com.techmatrix18.service.implementation;

import com.techmatrix18.model.Position;
import com.techmatrix18.repository.PositionRepository;
import com.techmatrix18.service.InterfacePosition;
import com.techmatrix18.service.InterfaceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link InterfacePosition} interface.
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@Service
public class PositionImpl implements InterfacePosition {
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
    public Position getPositionById(Long id) {
        return positionRepository.getById(id);
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

