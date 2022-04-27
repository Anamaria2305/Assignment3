package com.example.demo2;
import com.example.demo2.entity.*;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.IOrdersRepository;
import com.example.demo2.service.AES;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.OrdersService;
import com.example.demo2.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class OrdersServiceTest {
    @Mock
    private IOrdersRepository iOrdersRepository;
    @Mock
    private UserService userService;
    @Mock
    private JavaMailSender mailSender;
    @InjectMocks
    private OrdersService ordersService;

    @Test
    void getall(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        ordersList.add(new Orders("Pending",user1,foodsList));
        ordersList.add(new Orders("Accepted",user2,foodsList));

        given(iOrdersRepository.findAll()).willReturn(ordersList);
        List<Orders> actual=ordersService.getAll();
        assertEquals(ordersList, actual);
    }
    @Test
    void findByStatus(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        List<Orders> actual=ordersService.findByStatus("Pending");
        List<Orders> expected=new ArrayList<>();
        expected.add(order1);

        assertEquals(expected,actual);
    }
    @Test
    void findByStatusN(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        List<Orders> actual=ordersService.findByStatus("Declined");
        List<Orders> expected=new ArrayList<>();

        assertEquals(expected,actual);
    }

    @Test
    void findByActive(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Active",user1,foodsList);
        Orders order2=new Orders("Active",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        List<Orders> actual=ordersService.findByStatus("Active");
        List<Orders> expected=new ArrayList<>();
        expected.add(order1);
        expected.add(order2);
        assertEquals(expected,actual);
    }
    @Test
    void findByActiveN(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        List<Orders> actual=ordersService.findByStatus("Active");
        List<Orders> expected=new ArrayList<>();

        assertEquals(expected,actual);
    }
    @Test
    void testChangeDeclined(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        Orders actual=ordersService.changeStatusDeclined(0);

        order1.setStatus("Declined");

        assertEquals(order1,actual);
    }
    @Test
    void testChangeDeclinedN(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);

        given(iOrdersRepository.findAll()).willReturn(ordersList);

        Orders actual=ordersService.changeStatusDeclined(0);

        assertEquals(order1,actual);
    }
    @Test
    void testChangeStatusPen(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        Orders order3=new Orders("InDelivery",user2,foodsList);
        Orders order4=new Orders("Delivered",user1,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);
        ordersList.add(order3);
        ordersList.add(order4);

        given(iOrdersRepository.findAll()).willReturn(ordersList);
        Orders actual=ordersService.changeStatus(0);

        order1.setStatus("Accepted");
        assertEquals(order1,actual);
    }

    @Test
    void testChangeStatusAcc(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        Orders order3=new Orders("InDelivery",user2,foodsList);
        Orders order4=new Orders("Delivered",user1,foodsList);
        ordersList.add(order2);


        given(iOrdersRepository.findAll()).willReturn(ordersList);
        Orders actual=ordersService.changeStatus(0);

        order2.setStatus("InDelivery");
        assertEquals(order2,actual);
    }

    @Test
    void testChangeStatusInDel(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        Orders order3=new Orders("InDelivery",user2,foodsList);
        Orders order4=new Orders("Delivered",user1,foodsList);
        ordersList.add(order3);

        given(iOrdersRepository.findAll()).willReturn(ordersList);
        Orders actual=ordersService.changeStatus(0);

        order3.setStatus("Delivered");
        assertEquals(order3,actual);
    }
    @Test
    void testChangeStatusDel(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2","pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user2,foodsList);
        Orders order3=new Orders("InDelivery",user2,foodsList);
        Orders order4=new Orders("Delivered",user1,foodsList);
        ordersList.add(order4);

        given(iOrdersRepository.findAll()).willReturn(ordersList);
        Orders actual=ordersService.changeStatus(0);

        assertEquals(order4,actual);
    }
    @Test
    void getByUser(){
        User user1=new User("user", "pass","add");
        User user2=new User("user2", "pass2","add2");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));

        List<Orders> ordersList=new ArrayList<>();
        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Accepted",user1,foodsList);
        Orders order3=new Orders("InDelivery",user1,foodsList);
        Orders order4=new Orders("Delivered",user2,foodsList);
        ordersList.add(order1);
        ordersList.add(order2);
        ordersList.add(order3);
        ordersList.add(order4);

        given(userService.getById(1)).willReturn(user1);
        given(iOrdersRepository.findAll()).willReturn(ordersList);

        List<Orders> actual=ordersService.userOrder(1);
        List<Orders> expected=new ArrayList<>();
        expected.add(order1);
        expected.add(order2);
        expected.add(order3);

        assertEquals(expected,actual);
    }

    @Test
    void testSave(){
        User user1=new User("user", "pass","add");
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");

        List<Menu> menuList=new ArrayList<>();
        Menu men=new Menu("meniu");
        Admins admins=new Admins("admin2","admin");
        Restaurants res=new Restaurants("Res","Loc","zone",admins,men);
        men.setRestaurant(res);
        menuList.add(men);

        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2,menuList));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1,menuList));
        List<Orders> ordersList=new ArrayList<>();

        Orders order1=new Orders("Pending",user1,foodsList);
        Orders order2=new Orders("Pendingg",user1,foodsList);
        ordersList.add(order1);
        user1.setOrders(ordersList);
        given(userService.getById(1)).willReturn(user1);
        given(iOrdersRepository.save(order1)).willReturn(order1);

        Orders actual=ordersService.saveOrders(1,foodsList,order1,"Sd");

        assertEquals(order1,actual);

    }
}
