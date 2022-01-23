package ru.itimtim.springboot.springboot.dao;

import ru.itimtim.springboot.springboot.entities.Role;

import java.util.List;

public interface RoleDao {

    List<Role> allRole();
    Role findRoleById(Long id);
    void addRole(Role role);
}
