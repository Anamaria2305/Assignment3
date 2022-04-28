package com.example.demo2.service;

import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.entity.User;
import com.example.demo2.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepository iCategoryRepository;

    /**
     *
     * @param category - represents the category object we want to save in the DB
     * @return -the saved category entity including the id
     */
    public Category saveCategory(Category category){
        if(!category.getName().isEmpty() && category.getName()!=null){
            return iCategoryRepository.save(category);
        }
        else
        {
            System.out.println("Data is missing");
            return null;
        }
    }
    /**
     *
     * @return - a list with all the category objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<Category> getAll(){
        return (List<Category>) iCategoryRepository.findAll();
    }

    /**
     *
     *
     * @param name- represents the name of the category whose info we want to retrieve from DB
     * @return - category object with the specified name, or null if no such entry exists in DB
     */
    public Category findByName(String name){
        Optional<Category> category=iCategoryRepository.findAll().stream().filter(o->o.getName().equals(name)).findFirst();
        return category.orElse(null);
    }

}
