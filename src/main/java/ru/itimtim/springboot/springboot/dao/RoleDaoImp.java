package ru.itimtim.springboot.springboot.dao;

import org.springframework.stereotype.Repository;
import ru.itimtim.springboot.springboot.entities.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoleDaoImp implements RoleDao {

    @PersistenceContext()
    EntityManager entityManager;

    @Override
    public List<Role> allRole() {
        return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public Role findRoleById(Long id) throws NoSuchElementException {
        return entityManager.find(Role.class,id);
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
