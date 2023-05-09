package com.example.neksus.services;

import com.example.neksus.dao.UserDAO;
import com.example.neksus.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Optional<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User create(User user) {
        if (isUserValid(user)) {
            return userDAO.create(user);
        } else {
            throw new IllegalArgumentException("Invalid user data.");
        }
    }

    public boolean update(User user) {
        if (isUserValid(user)) {
            return userDAO.update(user);
        } else {
            throw new IllegalArgumentException("Invalid user data.");
        }
    }

    public boolean delete(String email) {
        return userDAO.delete(email);
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

        if (user.getIsBanned() != 'Y' && user.getIsBanned() != 'N') {
            return false;
        }

        if (user.getIsAdmin() != 'Y' && user.getIsAdmin() != 'N') {
            return false;
        }

        if (user.getRegisterDate() == null) {
            return false;
        }

        return true;
    }
}
