package com.ua.ToDo.controllers;

import com.ua.ToDo.entity.UserEntity;
import com.ua.ToDo.exceptions.UserExistException;
import com.ua.ToDo.exceptions.UserNotFoundException;
import com.ua.ToDo.model.User;
import com.ua.ToDo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> index(@RequestParam Long id) {
        ResponseEntity<?> response;
        try {
            User user = service.findById(id);
            response = new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody UserEntity entity) {
        ResponseEntity<?> response;
        try {
            service.save(entity);
            response = new ResponseEntity<>(HttpStatus.CREATED);
        } catch (UserExistException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable("id") Long id,
                                  @Valid @RequestBody UserEntity entity) {
        ResponseEntity<?> response;
        try {
            User userEdited = service.edit(id, entity);
            response = new ResponseEntity<>(userEdited, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_MODIFIED);
        }
        return response;
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam Long id) {
        ResponseEntity<?> response;
        try {
            Long idDeleted = service.delete(id);
            response = new ResponseEntity<>(idDeleted, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return response;
    }
}
