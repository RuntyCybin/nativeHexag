package com.hexagonal.repository.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hexagonal.domain.Role;
import com.hexagonal.domain.User;
import com.hexagonal.repository.RolesRepository;

public class RolesRepositoryImpl implements RolesRepository {

    private List<Role> cacheRoles = new ArrayList<>();

    @Override
    public void addRole(Role role) {
        cacheRoles.add(role);
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return cacheRoles.stream().filter(r -> r.getRoleName().equalsIgnoreCase(name)).findAny();
    }
}
