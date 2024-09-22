package com.springtableforportfolio.table.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springtableforportfolio.table.entities.User;
import com.springtableforportfolio.table.services.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers() {
        List<User> users = userService.getAllUsers();

        return users;

    }

    @PutMapping("/users/{nr}")
    public ResponseEntity<User> updateUser(@PathVariable int nr, @RequestBody User newUserDetails) {
        User updatedUser = userService.updateUser(nr, newUserDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{nr}")
    public ResponseEntity<Void> deleteUser(@PathVariable int nr) {
        userService.deleteUser(nr);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User newUser) {
        User createdUser = userService.saveUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

}
