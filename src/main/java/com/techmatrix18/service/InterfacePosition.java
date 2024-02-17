package com.techmatrix18.service;

import com.techmatrix18.model.*;
import java.util.List;

public interface InterfacePosition {
    List<Position> getAllPositions();
    Position getPositionById(Long id);

    boolean addPosition(Position position);
    boolean updatePosition(Position position);
    boolean deletePosition(Long id);
}

