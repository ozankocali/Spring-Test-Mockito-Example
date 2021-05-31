package com.ozeeesoftware.springtestmockito.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozeeesoftware.springtestmockito.controller.UserController;
import com.ozeeesoftware.springtestmockito.model.User;
import com.ozeeesoftware.springtestmockito.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserServiceImpl userService;

    String url="/api/v1/users";

    @Test
    public void testGetAllUsers()throws Exception{

        List<User> userList=new ArrayList<User>();
        userList.add(new User(1L,"test-username","test-firstName","test-lastName","test-email"));
        userList.add(new User(2L,"test-username2","test-firstName2","test-lastName2","test-email2"));

        when(userService.getAllUsers()).thenReturn(new ResponseEntity<List<User>>(userList, HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(userList);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }


    @Test
    public void testGetUserById()throws Exception{
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userService.getUserById(1L)).thenReturn(new ResponseEntity<User>(user,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/1")).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(user);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testGetUserByEmail()throws Exception{
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userService.getUserByEmail("test-email")).thenReturn(new ResponseEntity<User>(user,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/email/test-email")).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(user);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testGetUserByUsername()throws Exception{
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userService.getUserByUsername("test-username")).thenReturn(new ResponseEntity<User>(user,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(get(url+"/username/test-username")).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(user);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

    }

    @Test
    public void testCreateUser()throws Exception{
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userService.createUser(user)).thenReturn(new ResponseEntity<User>(user,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(post(url+"/save").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(user);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    public void testUpdateUser()throws Exception{
        User existingUser =new User(1L,"test-username","test-firstName","test-lastName","test-email");
        User updatedUser =new User(1L,"test-username2","test-firstName2","test-lastName2","test-email2");

        when(userService.updateUser(updatedUser)).thenReturn(new ResponseEntity<User>(updatedUser,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(put(url+"/update").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(updatedUser))).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(updatedUser);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);

        assertThat(actualJsonResponse).isNotEqualToIgnoringWhitespace(objectMapper.writeValueAsString(existingUser));
    }

    @Test
    public void testDeleteUser()throws Exception{
        User user =new User(1L,"test-username","test-firstName","test-lastName","test-email");

        when(userService.deleteUser(user)).thenReturn(new ResponseEntity<User>(user,HttpStatus.OK));

        MvcResult mvcResult=mockMvc.perform(delete(url+"/delete").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(user))).andExpect(status().isOk()).andReturn();

        String actualJsonResponse=mvcResult.getResponse().getContentAsString();

        String expectedJsonResponse=objectMapper.writeValueAsString(user);

        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

}
