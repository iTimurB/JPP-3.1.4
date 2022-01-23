package ru.itimtim.springboot.springboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itimtim.springboot.springboot.entities.User;
import ru.itimtim.springboot.springboot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> usersList = userService.allUser();
        if (usersList.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(usersList);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") long userId) {
        if (!userService.isUserExist(userId)){
            return ResponseEntity.notFound().build();
        }
        User showUser = userService.getUserById(userId);
        return ResponseEntity.ok().body(showUser);
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (!userService.isUserExist(user.getId())){
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(user);
        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        if (!userService.isUserExist(id)){
            return ResponseEntity.notFound().build();
        }
        userService.removeUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
