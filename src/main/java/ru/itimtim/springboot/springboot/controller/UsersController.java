package ru.itimtim.springboot.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itimtim.springboot.springboot.entities.User;
import ru.itimtim.springboot.springboot.service.RoleService;

@Controller
public class UsersController {

    private final RoleService roleService;

    @Autowired
    public UsersController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/user")
    public String getUserInfo(@AuthenticationPrincipal User user, Model model){
        model.addAttribute("user", user);
        return "home_page_for_all";
    }

    @GetMapping(value = "/admin")
    public String getAllUsers(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.allRole());
        return "admin_page";
    }
}
