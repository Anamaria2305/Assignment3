package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Orders {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name="orders_foods",
            joinColumns = {@JoinColumn(name="order_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn (name="food_id",referencedColumnName = "id")})
    private List<Food> food=new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public List<Food> getFood() {
        return food;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setFood(List<Food> food) {
        this.food = food;
    }

    public Orders(String status, User user, List<Food> food) {
        this.status = status;
        this.user = user;
        this.food = food;
    }

    public Orders() {
    }

    public Orders(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", user=" + user +
                ", food=" + food +
                '}';
    }
}
