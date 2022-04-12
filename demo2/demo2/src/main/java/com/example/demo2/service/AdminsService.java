package com.example.demo2.service;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.User;
import com.example.demo2.repository.IAdminsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminsService {

    @Autowired
    IAdminsRepository iAdminsRepository;

    public Admins getById(Integer id){
        Optional<Admins> admins=iAdminsRepository.findById(id);
        return admins.orElse(null);
    }

    public List<Admins> getAll(){
        return (List<Admins>) iAdminsRepository.findAll();
    }

    public void updateAdmin(Admins admin){
            iAdminsRepository.save(admin);
    }

    public Admins findByUsernameAndPass(String username, String password){
        Optional <Admins> admin=iAdminsRepository.findAll().stream().filter(o->o.getUsername().equals(username) && o.getPassword().equals(password)).findFirst();
        return admin.orElse(null);
    }
}
