package com.ua.ToDo.service;

import com.ua.ToDo.entity.ToDoEntity;
import com.ua.ToDo.entity.UserEntity;
import com.ua.ToDo.exceptions.ToDoNotFoundException;
import com.ua.ToDo.exceptions.UserNotFoundException;
import com.ua.ToDo.model.ToDo;
import com.ua.ToDo.repository.ToDoRepository;
import com.ua.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ToDoService {

    private final ToDoRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public ToDoService(ToDoRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }


    public ToDo save(ToDoEntity entity, Long user_id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user"));
        entity.setUserEntity(userEntity);
        ToDoEntity toDoSaved = repository.save(entity);
        return ToDo.toModel(toDoSaved);
    }

    public ToDo complete(Long id) throws ToDoNotFoundException {
        ToDoEntity entity = repository
                .findById(id)
                .orElseThrow(() -> new ToDoNotFoundException("Cannot find to do complete"));
        entity.setCompleted(true);
        ToDoEntity toDoEntityCompleted = repository.save(entity);
        return ToDo.toModel(toDoEntityCompleted);
    }
}
