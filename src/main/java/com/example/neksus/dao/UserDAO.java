package com.example.neksus.dao;

import com.example.neksus.models.User;

import java.util.List;

public interface UserDAO {
    User findById(Long id);

    List<User> findAll();

    User create(User user);

    boolean update(User user);

    boolean delete(Long id);
}
