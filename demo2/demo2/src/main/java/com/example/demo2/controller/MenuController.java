package com.example.demo2.controller;

import com.example.demo2.entity.Category;
import com.example.demo2.entity.Menu;
import com.example.demo2.service.MenuService;
import com.example.demo2.service.RestaurantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

@RequestMapping(value="/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Menu> getAll(){
        return menuService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveMenu(@RequestBody Menu menu,@RequestParam(name="restaurant") Integer id){
        menuService.saveMenu(menu,id);
    }

    @RequestMapping(method = RequestMethod.GET,value="/name")
    @ResponseBody
    public Menu getOne(@RequestParam(name="menu") String menu_name){
        return menuService.findByName(menu_name);
    }

    @RequestMapping(method = RequestMethod.POST,value="/restaurant")
    @ResponseBody
    public Menu getMenuRes(@RequestParam(name="restaurant") String rest){
        return menuService.findByRestaurant(rest);
    }
}
