package com.springtableforportfolio.table.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springtableforportfolio.table.entities.User;
import com.springtableforportfolio.table.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        initializeDummyData();
        return userRepository.findAll();
    }

    public User updateUser(int nr, User newUserDetails) {
        return userRepository.findById(nr).map(user -> {
            user.setName(newUserDetails.getName());
            user.setSur_name(newUserDetails.getSur_name());
            user.setEmail(newUserDetails.getEmail());
            user.setAge(newUserDetails.getAge());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found with nr: " + nr));
    }

    public void deleteUser(int nr) {
        if (!userRepository.existsById(nr)) {
            throw new RuntimeException("User not found with nr: " + nr);
        }
        userRepository.deleteById(nr);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    private void initializeDummyData() {
        if (userRepository.count() == 0) {
            User user1 = new User();
            user1.setNr(1);
            user1.setName("John");
            user1.setSur_name("Doe");
            user1.setEmail("john.doe@example.com");
            user1.setAge(30);

            User user2 = new User();
            user2.setNr(2);
            user2.setName("Jane");
            user2.setSur_name("Smith");
            user2.setEmail("jane.smith@example.com");
            user2.setAge(25);

            User user3 = new User();
            user3.setNr(3);
            user3.setName("Mike");
            user3.setSur_name("Johnson");
            user3.setEmail("mike.johnson@example.com");
            user3.setAge(35);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }

}
