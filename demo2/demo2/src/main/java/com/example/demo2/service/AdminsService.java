package com.example.demo2.service;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.User;
import com.example.demo2.repository.IAdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminsService {

    @Autowired
    IAdminsRepository iAdminsRepository;

    /**
     *
     *
     * @param id- represents the id of the admin whose info we want to retrieve from DB
     * @return - admin object with the specified id, or null if no such entry exists in DB
     */
    public Admins getById(Integer id){
        Optional<Admins> admins=iAdminsRepository.findById(id);
        return admins.orElse(null);
    }

    /**
     *
     * @return - a list with all the admin objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<Admins> getAll(){
        return (List<Admins>) iAdminsRepository.findAll();
    }

    /**
     *
     * @param username - represents the username of the admin whose info we want to check from DB
     * @param password - represents the password of the admin whose info we want to check from DB
     * @return - admin object with the specified username and password, or null if no such entry exists in DB
     */
    public Admins findByUsernameAndPass(String username, String password){
        Optional <Admins> admin=iAdminsRepository.findAll().stream().filter(o->o.getUsername().equals(username) && o.getPassword().equals(password)).findFirst();
        return admin.orElse(null);
    }


}
