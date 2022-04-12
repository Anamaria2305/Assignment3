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

    public void saveCategory(Category category){
        if(!category.getName().isEmpty() && category.getName()!=null){
            iCategoryRepository.save(category);
        }
        else
        {
            System.out.println("Data is missing");
        }
    }
    public List<Category> getAll(){
        return (List<Category>) iCategoryRepository.findAll();
    }

    public Category findByName(String name){
        Optional<Category> category=iCategoryRepository.findAll().stream().filter(o->o.getName().equals(name)).findFirst();
        return category.orElse(null);
    }

    public void updateCategory(Category category){
        iCategoryRepository.save(category);
    }
}
