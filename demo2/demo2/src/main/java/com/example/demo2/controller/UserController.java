package com.example.demo2.controller;

import com.example.demo2.entity.User;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<User> getAll(){
        return userService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @RequestMapping(method = RequestMethod.POST,value="/login")
    @ResponseBody
    public User logInUser(@RequestBody User user){
        return userService.findByUsernameAndPass(user.getUsername(),user.getPassword());
    }
}
