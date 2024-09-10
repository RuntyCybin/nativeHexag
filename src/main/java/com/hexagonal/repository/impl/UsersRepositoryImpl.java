package com.hexagonal.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hexagonal.domain.User;
import com.hexagonal.repository.UsersRepository;

public class UsersRepositoryImpl implements UsersRepository {

    List<User> cacheUsers = new ArrayList<>();

    @Override
    public void saveUser(User user) {
        cacheUsers.add(user);
    }

    @Override
    public Optional<User> getUserByName(String nameUser) {
        return cacheUsers.stream().filter(u -> u.getUserName().equalsIgnoreCase(nameUser)).findAny();
    }
}
