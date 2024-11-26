package com.techmatrix18.web.api;

import com.techmatrix18.model.Position;
import com.techmatrix18.service.PositionService;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

/**
 * Simple controller for Position
 *
 * @author Alexander Kuziv
 * @version 1.0
 */

@RestController
@RequestMapping("/api/v1/positions")
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping(path = "/test")
    public String getTest() throws ValidationException {
        return "Test";
    }

    @GetMapping(path = "/all")
    public List<Position> getPositions() throws ValidationException {
        return positionService.getAllPositions();
    }

    @GetMapping(path = "/{id}")
    public <Position, String> Object getOnePosition(@PathVariable Long id) throws ValidationException {
        //Long positionId = Long.parseLong(id);
        Optional<Position> position = (Optional<Position>) positionService.getPositionById(id);
        if (position.isPresent()) {
            return position.get();
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find position with ID=" + id + "\"}";
        }
    }

    @PostMapping(path = "/add")
    public @ResponseBody String addPosition (@RequestParam String title, @RequestParam String description) {
        Position p = new Position();
        p.setTitle(title);
        p.setDescription(description);
        if (positionService.addPosition(p)) {
            return "{\"status\": \"success\", \"message\": \"Position added successfully!\"}";
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't add position\"}";
        }
    }

    @PatchMapping(path = "/update")
    public @ResponseBody String updatePosition (@RequestParam Long positionId, @RequestParam String title, @RequestParam String description) {
        Optional<Position> p = positionService.getPositionById(positionId);
        if (p.get().getId() != null) {
            p.get().setTitle(title);
            p.get().setDescription(description);
            if (positionService.updatePosition(p.get())) {
                return "{\"status\": \"success\", \"message\": \"Position updated successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't update position\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find position with ID=" + positionId + "\"}";
        }
    }

    @DeleteMapping(path = "/delete/{positionId}", produces = "application/json;charset=UTF-8")
    public @ResponseBody String deletePosition (@PathVariable Long positionId) {
        Optional<Position> p = positionService.getPositionById(positionId);
        if (p.isPresent()) {
            if (positionService.deletePosition(positionId)) {
                return "{\"status\": \"success\", \"message\": \"Position deleted successfully!\"}";
            } else {
                return "{\"status\": \"error\", \"message\": \"Didn't delete position\"}";
            }
        } else {
            return "{\"status\": \"error\", \"message\": \"Didn't find position with ID=" + positionId + "\"}";
        }
    }
}

