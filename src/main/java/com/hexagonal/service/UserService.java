package com.hexagonal.service;

import com.hexagonal.domain.User;
import com.hexagonal.repository.UsersRepository;
import redis.clients.jedis.Jedis;

public class UserService {

    private final UsersRepository usersRepository;
    private final Jedis jedis;

    public UserService(UsersRepository users) {
        this.usersRepository = users;
        this.jedis = new Jedis("localhost", 6379);
    }

    /**
     * 
     * @param name the name to search by
     * @return User Object of User class
     */
    public User getUserByName(String name) {

        try {
            // Comprobar si una clave existe
            boolean exists = jedis.exists(name);

            if (exists) {
                System.out.println("Existe? " + exists);
                String nameUser = jedis.get(name);
                System.out.println("User obyained from the REDIS CACHE: " + nameUser);
                return usersRepository.getUserByName(nameUser).get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void saveOneUser(User user) {

        try {
            boolean exists = jedis.exists(user.getUserName());
            if (!exists) {
                jedis.set(user.getUserName(), user.getUserName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        usersRepository.saveUser(user);
    }
}
