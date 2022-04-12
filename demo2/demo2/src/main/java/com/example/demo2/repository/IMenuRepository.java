package com.example.demo2.repository;

import com.example.demo2.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMenuRepository extends JpaRepository<Menu,Integer> {
}
