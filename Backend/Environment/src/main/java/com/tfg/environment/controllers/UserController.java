package com.tfg.environment.controllers;

import com.tfg.environment.dtos.UserIdDto;
import com.tfg.environment.dtos.UserRequestDto;
import com.tfg.environment.dtos.UserResponseDto;
import com.tfg.environment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<UserIdDto> createUser(@RequestBody UserRequestDto data) {
        try {
            return ResponseEntity.ok(userService.createUser(data));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> readUser(@PathVariable("id") UserIdDto data) {
        try {
            return userService.readUser(data).map(
                    ResponseEntity::ok
            ).orElse(
                    ResponseEntity.notFound().build()
            );
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(path = "/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") UserIdDto id, @RequestBody UserRequestDto data) {
        try {
            return userService.updateUser(id, data) ? ResponseEntity.ok().build() :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UserIdDto data) {
        try {
            return userService.deleteUser(data) ? ResponseEntity.ok().build() :
                    ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
