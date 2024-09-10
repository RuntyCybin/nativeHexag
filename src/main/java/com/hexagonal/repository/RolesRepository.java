package com.hexagonal.repository;

import java.util.Optional;

import com.hexagonal.domain.Role;

public interface RolesRepository {

    void addRole(Role role);
    Optional<Role> getRoleByName(String name);

}
