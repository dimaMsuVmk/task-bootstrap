package ru.ivanov.bootmvc.service;

import ru.ivanov.bootmvc.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(long id);
    User findByUserName(String email);

    void updateUser(User updateUser);

    void removeUserById(long id);

    void save(User user);
    String getEncodedPassword(Long id);
}