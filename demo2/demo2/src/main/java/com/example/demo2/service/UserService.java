package com.example.demo2.service;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.User;
import com.example.demo2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    IUserRepository iUserRepository;


    final String secretKey = "ssshhhhhhhhhhh!!!!";

    /**
     *
     * @return - a list with all the user objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<User> getAll(){
        return (List<User>) iUserRepository.findAll();
    }

    /**
     *
     * @param user - represents the user object we want to save in the DB
     * @return -the saved user entity including the id
     */
    public User saveUser(User user){
        if(!user.getUsername().isEmpty() && user.getUsername()!=null){
        String notencrypted=user.getPassword();
        String encryptedPas=AES.encrypt(notencrypted,secretKey);
        user.setPassword(encryptedPas);
        return iUserRepository.save(user);
        }
        else
        {
            System.out.println("Data is missing");
            return null;
        }
    }

    /**
     *
     * @param username - represents the username of the user whose info we want to check from DB
     * @param password - represents the password of the user whose info we want to check from DB
     * @return - user object with the specified username and password, or null if no such entry exists in DB
     */
    public User findByUsernameAndPass(String username, String password){
        String encryptedPas=AES.encrypt(password,secretKey);
        Optional <User> user=iUserRepository.findAll().stream().filter(o->o.getUsername().equals(username) && o.getPassword().equals(encryptedPas)).findFirst();
        return user.orElse(null);
    }
    /**
     *
     *
     * @param id- represents the id of the user whose info we want to retrieve from DB
     * @return - user object with the specified id, or null if no such entry exists in DB
     */
    public User getById(Integer id){
        Optional<User> user=iUserRepository.findById(id);
        return user.orElse(null);
    }

}
