package com.example.demo2.service;

import com.example.demo2.entity.*;
import com.example.demo2.repository.IOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    @Autowired
    private JavaMailSender mailSender;

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

    public Orders saveOrders(Integer user_id, List<Food> food, Orders orders, String fd){
        if(!orders.getStatus().isEmpty() && orders.getStatus()!=null){


            User user=userService.getById(user_id);

            orders.setUser(user);
            orders.setFood(food);

            List<Orders> newOrder= user.getOrders();
            newOrder.add(orders);
            user.setOrders(newOrder);

            float s=0;
            String foodList="";

            for(Food f:food){
                s+=f.getPrice();
                foodList=foodList+" Food: " + f.getName()+ " with price " +f.getPrice()+ "lei, ";
                List<Orders> newOrders2=f.getOrder();
                newOrders2.add(orders);
                f.setOrder(newOrders2);

            }

            Food fod=food.get(0);
            Menu menu= fod.getMenu().get(0);
            Restaurants rest=menu.getRestaurant();
            Admins admins=rest.getAdmin();
            String adem= admins.getUsername();
            foodList=foodList.substring(0,foodList.length()-2);
            foodList=foodList+".";




            SimpleMailMessage mes=new SimpleMailMessage();
            mes.setFrom("anamariaraita@gmail.com");

            mes.setTo(adem);

            mes.setSubject("Invoice from FoodPanda");

            mes.setText("Order of user: "+ user.getUsername() + " with the address: "+ user.getAddress()+"\nWith the total price of: "
                    + s +" lei." +"\nIt contains: " + foodList + "\nSpecial details: " + fd);

            mailSender.send(mes);
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
