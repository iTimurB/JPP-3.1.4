package ru.itimtim.springboot.springboot.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itimtim.springboot.springboot.dao.UserDao;
import ru.itimtim.springboot.springboot.entities.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    private final UserDao userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserDao userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> allUser() {
        return userDAO.allUser();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDAO.addUser(user);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        String oldPassword = getUserById(user.getId()).getPassword();
        String newPassword = user.getPassword();
        if(!oldPassword.equals(newPassword)) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userDAO.updateUser(user);

    }

    @Override
    @Transactional
    public void removeUserById(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    public boolean isUserExist(long id) {
        return userDAO.isUserExist(id);
    }

    @Override
    public User getUserByNameWithRoles(String name) {
        return userDAO.getUserByNameWithRoles(name);
    }

}
