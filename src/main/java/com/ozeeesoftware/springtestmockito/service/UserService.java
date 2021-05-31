package com.ozeeesoftware.springtestmockito.service;

import com.ozeeesoftware.springtestmockito.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity getAllUsers();

    ResponseEntity getUserById(Long id);

    ResponseEntity getUserByEmail(String email);

    ResponseEntity getUserByUsername(String username);

    ResponseEntity createUser(User user);

    ResponseEntity updateUser(User user);

    ResponseEntity deleteUser(User user);

}
