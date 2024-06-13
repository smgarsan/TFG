package com.tfg.agents.controllers;

import com.tfg.agents.dtos.*;
import com.tfg.agents.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/maps")
public class MapController {

    @Autowired
    MapService mapService;

    @GetMapping("/{id}")
    public ResponseEntity<MapResponseDto> getMap(@PathVariable("id") String mapId) {
        try {
            Optional<MapResponseDto> mapResponseDtoOptional = mapService.getMap(mapId);
            return mapResponseDtoOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.badRequest().build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable("id") String mapId) {
        try {
            return mapService.deleteMap(mapId) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<MapNameResponseDto>> getNames() {
        try {
            return ResponseEntity.ok(mapService.getNames());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<MapIdResponseDto> saveMap(@RequestBody MapRequestDto map) {
        try {
            return ResponseEntity.ok(mapService.saveMap(map));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}/info")
    public ResponseEntity<MapInfoResponseDto> getInfo(@PathVariable("id") String id) {
        try {
            Optional<MapInfoResponseDto> mapResponseOptional = mapService.getInfo(id);

            return mapResponseOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}/obstacles")
    public ResponseEntity<MapObstaclesResponseDto> getObstacles(@PathVariable("id") String id) {
        try {
            Optional<MapObstaclesResponseDto> mapResponseOptional = mapService.getObstacles(id);

            return mapResponseOptional.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "{id}/obstacles/find")
    public ResponseEntity<MapObstaclesResponseDto> getObstaclesNearPosition(
            @PathVariable("id") String id,
            @RequestParam("x") int x,
            @RequestParam("y") int y,
            @RequestParam("distance") int distance) {
        try {
            Optional<MapObstaclesResponseDto> optionalDto = mapService.getObstaclesNearPosition(id, x, y, distance);
            return optionalDto.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
