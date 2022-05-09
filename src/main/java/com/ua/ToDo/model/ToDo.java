package com.ua.ToDo.model;

import com.ua.ToDo.entity.ToDoEntity;

public class ToDo {
    private final Long id;
    private final String title;
    private final String description;
    private final boolean completed;

    public ToDo(Long id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    public static ToDo toModel(ToDoEntity entity) {
        return new ToDo(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.isCompleted()
        );
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }
}
