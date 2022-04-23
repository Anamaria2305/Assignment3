package com.example.demo2.service;

import com.example.demo2.entity.*;
import com.example.demo2.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersService {

    @Autowired
    IOrdersRepository iOrdersRepository;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodServiced;


    public List<Orders> getAll(){
        return (List<Orders>) iOrdersRepository.findAll();
    }

    public List<Orders> findByStatus(String status){
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getStatus().equals(status)).collect(Collectors.toList());
        return orders;
    }

    public List<Orders> findByActive(Integer id){
        User user=userService.getById(id);
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getStatus().equals("Pending") || o.getStatus().equals("Accepted") || o.getStatus().equals("InDelivery")).collect(Collectors.toList());
        List<Orders> orders2=orders.stream().filter(o->o.getUser()==user).collect(Collectors.toList());
        return orders2;
    }

    public Orders saveOrders(Integer user_id, List<Food> food, Orders orders){
        if(!orders.getStatus().isEmpty() && orders.getStatus()!=null){


            User user=userService.getById(user_id);

            orders.setUser(user);
            orders.setFood(food);

            List<Orders> newOrder= user.getOrders();
            newOrder.add(orders);
            user.setOrders(newOrder);
            for(Food f:food){
                List<Orders> newOrders2=f.getOrder();
                newOrders2.add(orders);
                f.setOrder(newOrders2);
            }
            return iOrdersRepository.save(orders);
        }
        else
        {
            System.out.println("Data is missing");
            return null;
        }
    }

    public Orders changeStatusDeclined(Integer order_id){
        Orders orders=iOrdersRepository.findAll().stream().filter(o->o.getId()==order_id).findFirst().orElse(null);
        if(orders!=null){
            if(orders.getStatus().equals("Pending"))
            {orders.setStatus("Declined");
           iOrdersRepository.save(orders);}
        }
        else
            System.out.println("No order with that id");
        return orders;
    }
    public Orders changeStatus(Integer order_id){
        Orders orders=iOrdersRepository.findAll().stream().filter(o->o.getId()==order_id).findFirst().orElse(null);
        if(orders!=null){
            if(orders.getStatus().equals("Pending"))
                orders.setStatus("Accepted");
           else if(orders.getStatus().equals("Accepted"))
                orders.setStatus("InDelivery");
           else if(orders.getStatus().equals("InDelivery"))
                orders.setStatus("Delivered");
           else
                System.out.println("The order is completed");
            iOrdersRepository.save(orders);
        }
        else
            System.out.println("No order with that id");
        return orders;
    }

    public List<Orders> userOrder(Integer user_id){
        User user=userService.getById(user_id);
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getUser()==user).collect(Collectors.toList());
        return orders;
    }
}
