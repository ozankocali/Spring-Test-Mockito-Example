package com.ozeeesoftware.springtestmockito.user;

import com.ozeeesoftware.springtestmockito.model.User;
import com.ozeeesoftware.springtestmockito.repository.UserRepository;
import com.ozeeesoftware.springtestmockito.service.UserService;
import com.ozeeesoftware.springtestmockito.service.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetAllUsers(){
        List<User> userList=new ArrayList<User>();
        userList.add(new User(1L,"test-username","test-firstName","test-lastName","test-email"));
        userList.add(new User(2L,"test-username2","test-firstName2","test-lastName2","test-email2"));

        when(userRepository.findAll()).thenReturn(userList);

        assertEquals(2,userService.getAllUsers().getBody().size());

        assertEquals(userList,userService.getAllUsers().getBody());

    }

    @Test
    public void testGetUserById(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertEquals(user,userService.getUserById(1L).getBody());
    }

    @Test
    public void testGetUserByEmail(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userRepository.findUserByEmail("test-email")).thenReturn(user);

        assertEquals(user,userService.getUserByEmail("test-email").getBody());
    }

    @Test
    public void testGetUserByUsername(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userRepository.findUserByEmail("test-username")).thenReturn(user);

        assertEquals(user,userService.getUserByEmail("test-username").getBody());
    }

    @Test
    public void testCreateUser(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userRepository.save(user)).thenReturn(user);

        assertEquals(user,userService.createUser(user).getBody());

    }

    @Test
    public void testDeleteUser(){
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(user);

        verify(userRepository,times(1)).delete(user);

    }

}
