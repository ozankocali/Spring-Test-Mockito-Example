package com.ozeeesoftware.springtestmockito.user;

import com.ozeeesoftware.springtestmockito.model.User;
import com.ozeeesoftware.springtestmockito.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown(){
        userRepository.deleteAll();
    }

    @Test
    public void testFindUserByUsername(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");
        userRepository.save(user);

        User existingUser=userRepository.findUserByUsername("test-username");

        assertThat(existingUser).isNotNull();
        assertEquals(user,existingUser);

    }

    @Test
    public void testFindUserByEmail(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");
        userRepository.save(user);

        User existingUser=userRepository.findUserByEmail("test-email");

        assertThat(existingUser).isNotNull();
        assertEquals(user,existingUser);

    }



}
