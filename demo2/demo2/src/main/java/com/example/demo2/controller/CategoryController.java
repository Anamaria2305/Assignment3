package com.example.demo2.controller;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.User;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveCategory(@RequestBody Category category){
        categoryService.saveCategory(category);
    }

    @RequestMapping(method = RequestMethod.GET,value="/name")
    @ResponseBody
    public Category getOne(@RequestParam(name="category") String category_name){
        return categoryService.findByName(category_name);
    }
}
