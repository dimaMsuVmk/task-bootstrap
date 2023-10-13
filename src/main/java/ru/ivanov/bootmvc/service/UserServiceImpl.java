package ru.ivanov.bootmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ivanov.bootmvc.repository.UserDao;
import ru.ivanov.bootmvc.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    public User findByUserName(String email) {
        return userDao.findByEmail(email).get();
    }

    @Transactional
    @Override
    public void updateUser(User updateUser) {
        userDao.updateUser(updateUser);
    }

    @Transactional
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    @Transactional
    @Override
    public void save(User user) {
        userDao.save(user);
    }
    @Override
    public String getEncodedPassword(Long id){
        return userDao.getPassword(id);
    }
}