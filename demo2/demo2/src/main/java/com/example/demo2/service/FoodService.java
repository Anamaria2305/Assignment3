package com.example.demo2.service;

import com.example.demo2.entity.*;
import com.example.demo2.repository.IFoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    IFoodRepository iFoodRepository;

    @Autowired
    CategoryService categoryService;
    @Autowired
    MenuService menuService;

    /**
     *
     * @return - a list with all the food objects that exist in the DB and with the specific information
     *           for each one and sorted alphabetically
     */
    public List<Food> getAll(){
        List<Food> foods = (List<Food>) iFoodRepository.findAll();
        foods.sort(((o1, o2) -> o1.getCategory().getName().compareTo(o2.getCategory().getName())));
        return foods;
    }

    /**
     *
     * @param category_name - the name of the category of the food
     * @param food -represents the category object we want to save in the DB
     * @param menu_name - the name of the menu to which the food belongs
     * @return -the saved food entity including the id
     */
    public Food saveFood(String category_name, Food food,String menu_name){
        if(!food.getName().isEmpty() && food.getName()!=null){


            Category category=categoryService.findByName(category_name);


            Menu menu=menuService.findByName(menu_name);

            food.setCategory(category);


            List<Food> foodList= category.getFoods();
            foodList.add(food);
            category.setFoods(foodList);

            Food food1=iFoodRepository.save(food);

            List<Food> foodList2= menu.getFood();
            foodList2.add(food1);
            menu.setFood(foodList2);


            List<Menu> menuList= food1.getMenu();
            menuList.add(menu);
            food1.setMenu(menuList);
            System.out.println(menu.getId());
            return iFoodRepository.save(food1);
        }
        else
        {
            System.out.println("Data is missing");
            return null;
        }
    }

}
