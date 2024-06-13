package com.tfg.agents.controllers;

import com.tfg.agents.dtos.AbilityIdResponseDto;
import com.tfg.agents.dtos.AbilityRequestDto;
import com.tfg.agents.dtos.AbilityResponseDto;
import com.tfg.agents.services.AbilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/abilities")
public class AbilityController {

    @Autowired
    AbilityService abilityService;

    @GetMapping
    public ResponseEntity<List<AbilityResponseDto>> getAbilities() {
        try {
            return ResponseEntity.ok(abilityService.getAbilities());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<AbilityIdResponseDto> saveAbility(@RequestBody AbilityRequestDto ability) {
        try {
            return ResponseEntity.ok(abilityService.saveAbility(ability));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AbilityResponseDto> getAbility(@PathVariable("id") Long id) {
        try {
            return abilityService.getAbility(id).map(
                    ResponseEntity::ok
            ).orElse(
                    ResponseEntity.notFound().build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAbility(@PathVariable("id") Long id) {
        try {
            return abilityService.deleteAbility(id) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateAbility(@PathVariable("id") Long id, @RequestBody AbilityRequestDto data) {
        try {
            return abilityService.updateAbility(id, data) ? ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}