package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="user")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","orders"})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Orders> orders;
    public User() {

    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String username, String password, String address) {
        this.username = username;
        this.password = password;
        this.address = address;
    }

    public User(String username, String password, String address, List<Orders> orders) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", orders=" + orders +
                '}';
    }
}

