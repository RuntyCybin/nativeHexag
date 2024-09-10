package com.hexagonal;

import java.util.UUID;

import com.hexagonal.configuration.RedisCacheHandler;
import com.hexagonal.domain.Role;
import com.hexagonal.domain.User;
import com.hexagonal.repository.impl.UsersRepositoryImpl;
import com.hexagonal.service.UserService;
import com.hexagonal.service.UserServiceCustomCache;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        var usersRepository = new UsersRepositoryImpl();
        //var userService = new UserService(usersRepository);

        var userServiceCustCache = new UserServiceCustomCache(usersRepository);
        var cacheService = (UserServiceCustomCache) RedisCacheHandler.createProxy(userServiceCustCache);

        Role role = Role
                .builder()
                .roleId(UUID.randomUUID())
                .roleName("normal")
                .build();

        User user = User
                .builder()
                .userId(UUID.randomUUID())
                .userName("Jhon Doo")
                .userRole(role)
                .build();

        //userService.saveOneUser(user);
        //User response = userService.getUserByName("Jhon Doo");
        //System.out.println("The searched user id is: " + response.getUserId());

        cacheService.saveOneUser(user);
        System.out.println("From ddbb: " + cacheService.getUserByName(user.getUserName()));

        System.out.println("From CACHE: " + cacheService.getUserByName(user.getUserName()));
        
    }
}