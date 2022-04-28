package com.example.demo2.service;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.entity.User;
import com.example.demo2.repository.IRestaurantsRepository;
import com.example.demo2.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantsService {
    @Autowired
    IRestaurantsRepository iRestaurantsRepository;
    @Autowired
    AdminsService adminsService;

    /**
     *
     * @param restaurant - represents the restaurant object we want to save in the DB
     * @param admin_id -represents the id of the admin who administrates the restaurant we want to save
     * @return -the saved restaurant entity including the id
     */
    public Restaurants saveRestaurant( Restaurants restaurant,Integer admin_id){
        if(!restaurant.getName().isEmpty() && restaurant.getName()!=null){
            Admins admin=adminsService.getById(admin_id);
            restaurant.setAdmin(admin);
            admin.setRestaurant(restaurant);
            return iRestaurantsRepository.save(restaurant);
        }
        else
        {
            System.out.println("Data is missing");
            return null;
        }
    }
    /**
     *
     * @return - a list with all the restaurant objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<Restaurants> getAll(){
        return (List<Restaurants>) iRestaurantsRepository.findAll();
    }
    /**
     *
     *
     * @param id- represents the id of the restaurant whose info we want to retrieve from DB
     * @return - restaurant object with the specified id, or null if no such entry exists in DB
     */
    public Restaurants getById(Integer id){
        Optional<Restaurants> restaurant=iRestaurantsRepository.findById(id);
        return restaurant.orElse(null);
    }
    /**
     *
     *
     * @param name- represents the name of the restaurant whose info we want to retrieve from DB
     * @return - restaurant object with the specified name, or null if no such entry exists in DB
     */
    public Restaurants findByName(String name){
        Optional<Restaurants> restaurant=iRestaurantsRepository.findAll().stream().filter(o->o.getName().equals(name)).findFirst();
        return restaurant.orElse(null);
    }

}
