package com.techmatrix18.web.api;

import com.techmatrix18.model.Position;
import com.techmatrix18.repository.PositionRepository;
import com.techmatrix18.service.implementation.PositionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;

/**
 * Simple controller for Position
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PositionServiceImpl positionService;

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Position> getPositions() throws ValidationException {
        return positionService.getAllPositions();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addPosition (@RequestParam String title, @RequestParam String description) {
        Position p = new Position();
        p.setTitle(title);
        p.setDescription(description);
        positionService.addPosition(p);
        return "Saved";
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updatePosition (@RequestParam Long positionId, @RequestParam String title, @RequestParam String description) {
        Position p = positionService.getPositionById(positionId);
        if (p.getId() != null) {
            p.setTitle(title);
            p.setDescription(description);
            positionService.updatePosition(p);
        }
        return "Updated";
    }

    @DeleteMapping(path = "/delete/{positionId:\\\\d+}")
    public @ResponseBody String deletePosition (@PathVariable Long positionId) {
        Position p = positionService.getPositionById(positionId);
        if (p.getId() != null) {
            positionService.deletePosition(positionId);
        }
        return "Deleted";
    }
}

