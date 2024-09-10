package com.hexagonal.repository;

import java.util.Optional;

import com.hexagonal.domain.User;

public interface UsersRepository {

    void saveUser(User user);
    Optional<User> getUserByName(String nameUser);

}
