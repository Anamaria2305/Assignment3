package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="admins")

public class Admins {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    @Column
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id", referencedColumnName = "id")
    private Restaurants restaurant;

    public int getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public Restaurants getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurants restaurant) {
        this.restaurant = restaurant;
    }

    public Admins(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Admins(String username, String password, Restaurants restaurant) {
        this.username = username;
        this.password = password;
        this.restaurant = restaurant;
    }

    public Admins() {
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
