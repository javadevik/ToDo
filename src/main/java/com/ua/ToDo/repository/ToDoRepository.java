package com.ua.ToDo.repository;

import com.ua.ToDo.entity.ToDoEntity;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<ToDoEntity, Long> {
    ToDoEntity findToDoEntityByTitle(String title);
}