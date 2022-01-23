package ru.itimtim.springboot.springboot.service;

import org.springframework.stereotype.Service;
import ru.itimtim.springboot.springboot.dao.RoleDao;
import ru.itimtim.springboot.springboot.entities.Role;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImp implements RoleService {

    private final RoleDao roleDAO;

    public RoleServiceImp(RoleDao roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public List<Role> allRole() {
        return roleDAO.allRole();
    }

    @Override
    public Role findRoleById(Long id) {
        return roleDAO.findRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.addRole(role);
    }

}
