package com.example.demo2.controller;


import com.example.demo2.entity.Category;
import com.example.demo2.entity.Food;
import com.example.demo2.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Food> getAll(){
        return foodService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveFood(@RequestBody Food food,@RequestParam(name="category") String category,@RequestParam(name="menu") String menu){
        foodService.saveFood(category,food,menu);
    }
    @RequestMapping(method = RequestMethod.POST, value="/amount")
    @ResponseBody
    public float saveFood(@RequestBody List<Food> food){
        return foodService.calculateAmount(food);
    }

}
