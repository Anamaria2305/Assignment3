package com.example.demo2.controller;


import com.example.demo2.entity.Admins;
import com.example.demo2.entity.User;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/admins")
public class AdminsController {

    @Autowired
    AdminsService adminsService;

    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Admins> getAll(){
        return adminsService.getAll();
    }

    @RequestMapping(method = RequestMethod.GET,value="/id")
    @ResponseBody
    public Admins getOne(@RequestParam(name="id") Integer admin_id){
        return adminsService.getById(admin_id);
    }
    @RequestMapping(method = RequestMethod.POST,value="/login")
    @ResponseBody
    public Admins logInAdmins(@RequestBody Admins admin){
        return adminsService.findByUsernameAndPass(admin.getUsername(),admin.getPassword());
    }
}
