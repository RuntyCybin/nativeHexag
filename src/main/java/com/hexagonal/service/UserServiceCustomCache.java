package com.hexagonal.service;

import com.hexagonal.configuration.RedisCache;
import com.hexagonal.domain.User;
import com.hexagonal.repository.UsersRepository;

public class UserServiceCustomCache {

    private final UsersRepository usersRepository;

    public UserServiceCustomCache(UsersRepository users) {
        this.usersRepository = users;
    }

    @RedisCache(valueNameString = "usuario", valueKey = "nombre")
    public User getUserByName(String name) {

        try {
            return usersRepository.getUserByName(name).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveOneUser(User user) {

        try {
            usersRepository.saveUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
