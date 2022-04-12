package com.example.demo2.controller;

import com.example.demo2.entity.Food;
import com.example.demo2.entity.Orders;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.service.CategoryService;
import com.example.demo2.service.FoodService;
import com.example.demo2.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value="/orders")
public class OrdersController {

    @Autowired
    OrdersService ordersService;
    @Autowired
    FoodService foodService;
    @RequestMapping(method = RequestMethod.GET,value="/all")
    @ResponseBody
    public List<Orders> getAll(){
        return ordersService.getAll();
    }

    @RequestMapping(method = RequestMethod.POST,value="/active")
    @ResponseBody
    public List<Orders> getActive(@RequestParam(name="user_id") Integer id){
        return ordersService.findByActive(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/save")
    @ResponseBody
    public void saveOrders(@RequestBody Orders orders, @RequestParam(name="user") Integer user_id, @RequestParam(name="foods") List<Integer> foods_id){
        List<Food> foodList=new ArrayList<>();
        for (Integer idf:foods_id) {
             foodList.addAll(foodService.getAll().stream().filter(o->o.getId()==idf).collect(Collectors.toList()));
        }
        ordersService.saveOrders(user_id,foodList,orders);
    }
    @RequestMapping(method = RequestMethod.POST,value="/declined")
    @ResponseBody
    public Orders changeStatusDeclined(@RequestParam(name="order_id") Integer id){
        return ordersService.changeStatusDeclined(id);
    }
    @RequestMapping(method = RequestMethod.POST,value="/status")
    @ResponseBody
    public Orders changeStatus(@RequestParam(name="order_id") Integer id){
        return ordersService.changeStatus(id);
    }
    @RequestMapping(method = RequestMethod.POST,value="/userorder")
    @ResponseBody
    public List<Orders> userOrder(@RequestParam(name="user_id") Integer id){
        return ordersService.userOrder(id);
    }
    @RequestMapping(method = RequestMethod.POST,value="/findbtstatus")
    @ResponseBody
    public List<Orders> userOrder(@RequestParam(name="status") String status){
        return ordersService.findByStatus(status);
    }
}
