package com.example.demo2.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name="restaurants")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","admin"})
public class Restaurants {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String zones;
    @OneToOne(mappedBy = "restaurant",cascade=CascadeType.ALL)
    private Admins admin;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", referencedColumnName = "id")
    private Menu menu;

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZones() {
        return zones;
    }

    public void setZones(String zones) {
        this.zones = zones;
    }

    public Admins getAdmin() {
        return admin;
    }

    public void setAdmin(Admins admin) {
        this.admin = admin;
    }



    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Restaurants(String name, String location, String zones, Admins admin) {
        this.name = name;
        this.location = location;
        this.zones = zones;
        this.admin = admin;
    }

    public Restaurants(String name, String location, String zones, Admins admin, Menu menu) {
        this.name = name;
        this.location = location;
        this.zones = zones;
        this.admin = admin;
        this.menu = menu;
    }

    public Restaurants(String name, String location, String zones) {
        this.name = name;
        this.location = location;
        this.zones = zones;
    }

    public Restaurants() {
    }

    @Override
    public String toString() {
        return "Restaurants{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", zones='" + zones + '\'' +
                ", admin=" + admin +
                ", menu=" + menu +
                '}';
    }
}
