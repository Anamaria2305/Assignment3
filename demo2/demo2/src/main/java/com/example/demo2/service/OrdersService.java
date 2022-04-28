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

    /**
     *
     * @return - a list with all the order objects that exist in the DB and with the specific information
     *           for each one
     */
    public List<Orders> getAll(){
        return (List<Orders>) iOrdersRepository.findAll();
    }

    /**
     *
     * @param status - represents the status of the orders whose info we want to retrieve from DB
     * @return - a list with all orders retrieved from the DB which have the specified status and their info
     */
    public List<Orders> findByStatus(String status){
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getStatus().equals(status)).collect(Collectors.toList());
        return orders;
    }

    /**
     *
     * @param id - the id of the user whose orders we want to retrieve from DB
     * @return - a list with the orders of the user we specified which have status "Pending"
     * or "Active" or "InDelivery"
     */
    public List<Orders> findByActive(Integer id){
        User user=userService.getById(id);
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getStatus().equals("Pending") || o.getStatus().equals("Accepted") || o.getStatus().equals("InDelivery")).collect(Collectors.toList());
        List<Orders> orders2=orders.stream().filter(o->o.getUser()==user).collect(Collectors.toList());
        return orders2;
    }

    /**
     *
     * @param user_id - the id of the user who placed the order
     * @param food - a list with the foods present in this order
     * @param orders - the order object we want to save in the DB
     * @param fd - special details added by the user to the order
     * @return - the order object which was saved in the DB
     *
     * This method also sends an e-mail from a specified e-mail addres
     * to the e-mail addres of the admin which has the restaurant where the foods in the orders are.
     * It sends info such as foods in the order, total price, info about the user and special details.
     */
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

    /**
     *
     * @param order_id - the id of the order whose status we want to change to "Declined"
     * @return - the order object with the updated info
     */
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

    /**
     *
     * @param order_id - the id of the order whose status we want to change
     * @return - the order object with the updated info
     * This method takes into account the status of the order and changes it accordingly.
     * The flux is: Pending->Accepted->InDelivery->Delivered
     */
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

    /**
     *
     * @param user_id - this represents the id of the user which had placed some orders
     * @return - a list of orders which all belong to the user with id specified as input
     */
    public List<Orders> userOrder(Integer user_id){
        User user=userService.getById(user_id);
        List<Orders> orders=iOrdersRepository.findAll().stream().filter(o->o.getUser()==user).collect(Collectors.toList());
        return orders;
    }
}
