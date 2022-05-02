package com.ua.ToDo.model;

import com.ua.ToDo.entity.UserEntity;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class User {
    private final Long id;
    private final String username;
    private final List<ToDo> todos;

    public User(Long id, String username, List<ToDo> todos) {
        this.id = id;
        this.username = username;
        this.todos = todos;
    }

    public static User toModel(@NotNull UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getTodos()
                        .stream()
                        .map(ToDo::toModel)
                        .collect(Collectors.toList()));
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<ToDo> getTodos() {
        return todos;
    }
}
