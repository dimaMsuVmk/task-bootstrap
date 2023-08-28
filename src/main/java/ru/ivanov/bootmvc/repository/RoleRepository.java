package ru.ivanov.bootmvc.repository;

import ru.ivanov.bootmvc.model.Role;
import ru.ivanov.bootmvc.model.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleRepository {
    List<Role> findAll();
    Role findRoleByAuthority(String authority) throws NoSuchElementException;
    void save(Role role);
    Role getRoleByName(String name);
}
