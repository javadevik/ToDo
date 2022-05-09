package com.ua.ToDo.controllers;

import com.ua.ToDo.entity.ToDoEntity;
import com.ua.ToDo.exceptions.ToDoNotFoundException;
import com.ua.ToDo.exceptions.UserNotFoundException;
import com.ua.ToDo.model.ToDo;
import com.ua.ToDo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService service;

    @Autowired
    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam Long user_id,
                                    @Valid @RequestBody ToDoEntity entity) {
        ResponseEntity<?> response;
        try {
            ToDo toDo = service.save(entity, user_id);
            response = new ResponseEntity<>(toDo, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return response;
    }

    @PatchMapping
    public ResponseEntity<?> completeToDo(@RequestParam Long id) {
        ResponseEntity<?> response;
        try {
            ToDo toDoCompleted = service.complete(id);
            response = new ResponseEntity<>(toDoCompleted, HttpStatus.OK);
        } catch (ToDoNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        return response;
    }
}
