package com.example.demo2.controller;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.entity.User;
import com.example.demo2.service.RestaurantsService;
import com.example.demo2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/restaurants")
public class RestaurantController {

    @Autowired
    RestaurantsService restaurantsService;

    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Restaurants> getAll(){
        return restaurantsService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveRestaurant(@RequestBody Restaurants restaurant, @RequestParam(name="admin_id") Integer admin_id){
        restaurantsService.saveRestaurant(restaurant,admin_id);
    }

    @RequestMapping(method = RequestMethod.GET,value="/id")
    @ResponseBody
    public Restaurants getOne(@RequestParam(name="id") Integer id){
        return restaurantsService.getById(id);
    }

}
