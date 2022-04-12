package com.example.demo2.repository;

import com.example.demo2.entity.Restaurants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestaurantsRepository extends JpaRepository<Restaurants,Integer> {
}
