package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="food")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","menu","order","user"})
public class Food {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Float price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToMany(mappedBy = "food",cascade = CascadeType.MERGE)
    private List<Menu> menu=new ArrayList<>();

    @ManyToMany(mappedBy = "food",cascade = CascadeType.MERGE)
    private List<Orders> order=new ArrayList<>();

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public List<Menu> getMenu() {
        return menu;
    }

    public void setMenu(List<Menu> menu) {
        this.menu = menu;
    }

    public Food() {
    }

    public List<Orders> getOrder() {
        return order;
    }

    public void setOrder(List<Orders> order) {
        this.order = order;
    }

    public Food(String name, String description, Float price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public Food(String name, String description, Float price, Category category, List<Menu> menu) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", menu=" + menu +
                '}';
    }
}
