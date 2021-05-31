package com.ozeeesoftware.springtestmockito.service;

import com.ozeeesoftware.springtestmockito.model.User;
import com.ozeeesoftware.springtestmockito.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {

        User user=userRepository.findById(id).orElseThrow(null);

        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {

        return new ResponseEntity<User>(userRepository.findUserByEmail(email),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        return new ResponseEntity<User>(userRepository.findUserByUsername(username),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return new ResponseEntity<User>(userRepository.save(user),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {

        User existingUser=userRepository.findById(user.getId()).orElseThrow(null);

        existingUser.setUsername(user.getUsername());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User updatedUser=userRepository.save(existingUser);

        return new ResponseEntity<User>(updatedUser,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(User user) {

        User existingUser=userRepository.findById(user.getId()).orElseThrow(null);

        userRepository.delete(user);

        return new ResponseEntity<User>(existingUser,HttpStatus.OK);
    }
}
