package com.example.demo2.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="category")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","foods"})
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Food> foods;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public Category() {
    }

    public Category(String name, List<Food> foods) {
        this.name = name;
        this.foods = foods;
    }

    public Category(String name) {
        this.name = name;
    }
}
