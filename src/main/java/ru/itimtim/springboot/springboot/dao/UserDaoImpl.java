package ru.itimtim.springboot.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.itimtim.springboot.springboot.entities.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext()
    EntityManager entityManager;


    @Override
    public List<User> allUser() {
        return entityManager.createQuery("SELECT u from User u", User.class).getResultList();
    }

    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User updateUser(User user) {
        entityManager.merge(user);
        return user;
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public boolean isUserExist(long id) {
        return !entityManager.createQuery("SELECT 1 FROM User WHERE EXISTS (SELECT 1 FROM User u WHERE u.id = :id)").setParameter("id", id).setMaxResults(1).getResultList().isEmpty();
    }

    @Override
    public User getUserByNameWithRoles(String email) {
        return entityManager.createQuery("SELECT u FROM User u join fetch u.roles WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
