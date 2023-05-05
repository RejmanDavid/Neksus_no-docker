package com.example.neksus.services.impl;

import com.example.neksus.dao.UserDAO;
import com.example.neksus.dao.impl.UserDAOImpl;
import com.example.neksus.models.User;
import com.example.neksus.services.UserService;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    public UserServiceImpl() {
        userDAO = new UserDAOImpl();
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User create(User user) {
        if (isUserValid(user)) {
            return userDAO.create(user);
        } else {
            throw new IllegalArgumentException("Invalid user data.");
        }
    }

    @Override
    public boolean update(User user) {
        if (isUserValid(user)) {
            return userDAO.update(user);
        } else {
            throw new IllegalArgumentException("Invalid user data.");
        }
    }

    @Override
    public boolean delete(Long id) {
        return userDAO.delete(id);
    }

    private boolean isUserValid(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().trim().isEmpty() || !user.getEmail().contains("@")) {
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty() || user.getPassword().length() < 6) {
            return false;
        }

        return true;
    }
}