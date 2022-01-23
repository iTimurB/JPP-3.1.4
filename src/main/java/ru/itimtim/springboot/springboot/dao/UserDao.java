package ru.itimtim.springboot.springboot.dao;

import ru.itimtim.springboot.springboot.entities.User;

import java.util.List;

public interface UserDao {

    List<User> allUser();
    User addUser(User user);
    User getUserById(long id);
    User updateUser(User user);
    void removeUserById(long id);
    boolean isUserExist(long id);
    User getUserByNameWithRoles(String name);

}
