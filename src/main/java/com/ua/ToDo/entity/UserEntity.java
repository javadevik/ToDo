package com.ua.ToDo.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(min = 3, max = 30, message = "Username should be between 3 and 30 characters")
    private String username;

    @NotNull
    @Size(min = 8, max = 30, message = "Password is no correct")
    private char[] password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userEntity")
    private List<ToDoEntity> todos;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ToDoEntity> getTodos() {
        return todos;
    }

    public void setTodos(List<ToDoEntity> todos) {
        this.todos = todos;
    }
}
