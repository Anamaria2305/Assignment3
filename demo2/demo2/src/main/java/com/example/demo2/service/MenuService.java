package com.example.demo2.service;

import com.example.demo2.entity.*;
import com.example.demo2.repository.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    IMenuRepository iMenuRepository;
    @Autowired
    RestaurantsService restaurantsService;

    /**
     *
     * @param menu - represents the menu object we want to save in the DB
     * @param id - represents the id of the restaurant to which the menu belongs
     * @return -the saved menu entity including the id
     */
    public Menu saveMenu(Menu menu,Integer id){
        if(!menu.getName().isEmpty() && menu.getName()!=null){

            Restaurants restaurant=restaurantsService.getById(id);
            menu.setRestaurant(restaurant);
            restaurant.setMenu(menu);
            return iMenuRepository.save(menu);
        }
        else
        {
            System.out.println("Data is missing");
            return null;

        }
    }
    /**
     *
     * @return - a list with all the menu objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<Menu> getAll(){
        return (List<Menu>) iMenuRepository.findAll();
    }
    /**
     *
     *
     * @param name- represents the name of the menu whose info we want to retrieve from DB
     * @return - category object with the specified name, or null if no such entry exists in DB
     */
    public Menu findByName(String name){
        Optional<Menu> menu=iMenuRepository.findAll().stream().filter(o->o.getName().equals(name)).findFirst();
        return menu.orElse(null);
    }
    /**
     *
     *
     * @param name- represents the name of the restaurant to which the menu whose info we want to retrieve from DB belongs
     * @return - menu object which belongs to the specified restaurant, or null if no such entry exists in DB
     */
    public Menu findByRestaurant(String name){
        Restaurants restaurants=restaurantsService.findByName(name);
        Optional<Menu> menu=iMenuRepository.findAll().stream().filter(o->o.getRestaurant().equals(restaurants)).findFirst();
        List<Food> foods=menu.get().getFood();
        foods.sort(((o1, o2) -> o1.getCategory().getName().compareTo(o2.getCategory().getName())));
        menu.get().setFood(foods);
        return menu.orElse(null);
    }
}
