package com.example.demo.dao;

import com.example.demo.entity.User;

public interface UserDao {

    public int addUser(User user);

    public User findUserById(String id);

    public User findUserByKahao(String kahao);

    public int updateUser(User user);
}
