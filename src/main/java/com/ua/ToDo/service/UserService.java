package com.ua.ToDo.service;

import com.ua.ToDo.entity.UserEntity;
import com.ua.ToDo.exceptions.UserExistException;
import com.ua.ToDo.exceptions.UserNotFoundException;
import com.ua.ToDo.model.User;
import com.ua.ToDo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User findById(long id) throws UserNotFoundException {
        UserEntity entity = repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user"));
        return User.toModel(entity);
    }

    public void save(UserEntity entity) throws UserExistException {
        if (repository.findByUsername(entity.getUsername()) != null)
            throw new UserExistException("User is already exists");
        repository.save(entity);
    }

    public User edit(long id, UserEntity entity) throws UserNotFoundException, UserExistException {
        UserEntity entityToEdit = repository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException("Cannot find user"));

        entityToEdit.setUsername(entity.getUsername());
        entityToEdit.setPassword(entity.getPassword());
        repository.save(entityToEdit);

        return User.toModel(entityToEdit);
    }

    public Long delete(Long id) throws UserNotFoundException {
        Long idUserToDelete = findById(id).getId();
        repository.deleteById(idUserToDelete);
        return idUserToDelete;
    }
}
