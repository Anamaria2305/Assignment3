package com.example.demo2.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="menu")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","restaurant"})
public class Menu {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;


    @OneToOne(mappedBy = "menu",cascade=CascadeType.ALL)
    private Restaurants restaurant;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name="menufoods",
            joinColumns = {@JoinColumn(name="menu_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn (name="food_id",referencedColumnName = "id")})
    private List<Food> food=new ArrayList<>();

    public String getName() {
        return name;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public List<Food> getFood() {
        return food;
    }

    public Menu() {
    }



    public void setFood(List<Food> food) {
        this.food = food;
    }

    public Menu(String name,List<Food> food) {
        this.name = name;
        this.food = food;
    }

    public Menu(String name) {
        this.name = name;
    }

    public Menu(String name, Restaurants restaurant, List<Food> food) {
        this.name = name;
        this.restaurant = restaurant;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", food=" + food +
                '}';
    }
}
