package ru.ivanov.bootmvc.dao;

import ru.ivanov.bootmvc.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDao {
    List<Role> findAll();
    Role findRoleByAuthority(String authority) throws NoSuchElementException;
    void save(Role role);
    Role getRoleByName(String name);
}
