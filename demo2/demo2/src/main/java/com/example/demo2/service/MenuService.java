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
    public List<Menu> getAll(){
        return (List<Menu>) iMenuRepository.findAll();
    }

    public Menu findByName(String name){
        Optional<Menu> menu=iMenuRepository.findAll().stream().filter(o->o.getName().equals(name)).findFirst();
        return menu.orElse(null);
    }
    public Menu findByRestaurant(String name){
        Restaurants restaurants=restaurantsService.findByName(name);
        Optional<Menu> menu=iMenuRepository.findAll().stream().filter(o->o.getRestaurant().equals(restaurants)).findFirst();
        List<Food> foods=menu.get().getFood();
        foods.sort(((o1, o2) -> o1.getCategory().getName().compareTo(o2.getCategory().getName())));
        menu.get().setFood(foods);
        return menu.orElse(null);
    }
}
