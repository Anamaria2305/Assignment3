package com.example.demo2;
import com.example.demo2.entity.*;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.IMenuRepository;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.MenuService;
import com.example.demo2.service.RestaurantsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class MenuServiceTest {
    @Mock
    private IMenuRepository iMenuRepository;
    @Mock
    private RestaurantsService restaurantsService;
    @InjectMocks
    private MenuService menuService;

    @Test
    void testAll(){
        List<Menu> menuList=new ArrayList<>();
        menuList.add(new Menu("Meniul1"));
        menuList.add(new Menu("Meniul2"));
        menuList.add(new Menu("Meniul3"));
        given(iMenuRepository.findAll()).willReturn(menuList);
        List<Menu> actual=menuService.getAll();
        assertEquals(menuList, actual);
    }
    @Test
    void testFN(){
        List<Menu> menuList=new ArrayList<>();
        menuList.add(new Menu("Meniul1"));
        menuList.add(new Menu("Meniul2"));
        menuList.add(new Menu("Meniul3"));
        given(iMenuRepository.findAll()).willReturn(menuList);
        Menu actual=menuService.findByName("Meniul3");
        assertEquals(menuList.get(2),actual);
    }
    @Test
    void testFNN(){
        List<Menu> menuList=new ArrayList<>();
        menuList.add(new Menu("Meniul1"));
        menuList.add(new Menu("Meniul2"));
        menuList.add(new Menu("Meniul3"));
        given(iMenuRepository.findAll()).willReturn(menuList);
        Menu actual=menuService.findByName("Meniul4");
        assertEquals(null,actual);
    }
    @Test
    void testFR(){

        Menu men=new Menu("meniu");
        Admins admins=new Admins("admin","admin");
        Category categ1=new Category("Drinks");
        List<Menu> ml=new ArrayList<>();
        ml.add(men);
        Food existing=new Food("Food2","descr2",(float)12.6,categ1,ml);
        List<Food> fl=new ArrayList<>();
        fl.add(existing);
        men.setFood(fl);


        Restaurants res=new Restaurants("Res","Loc","zone",admins,men);
        men.setRestaurant(res);
        given(restaurantsService.findByName("Res")).willReturn(res);
        given(iMenuRepository.findAll()).willReturn(ml);

        Menu actual=menuService.findByRestaurant("Res");
        assertEquals(men,actual);
    }
    @Test
    void testFRN(){
        Menu men=new Menu("meniu");
        Admins admins=new Admins("admin","admin");
        Category categ1=new Category("Drinks");
        List<Menu> ml=new ArrayList<>();
        ml.add(men);
        Food existing=new Food("Food2","descr2",(float)12.6,categ1,ml);
        List<Food> fl=new ArrayList<>();
        fl.add(existing);
        men.setFood(fl);


        Restaurants res=new Restaurants("Res","Loc","zone",admins,men);
        men.setRestaurant(res);
        given(restaurantsService.findByName("Res")).willReturn(null);
        given(iMenuRepository.findAll()).willReturn(ml);
        Throwable exception = assertThrows(NoSuchElementException.class, () -> menuService.findByRestaurant("Res"));
        assertEquals("No value present", exception.getMessage());
    }

    @Test
    void testSave(){
        Menu men=new Menu("meniu");
        Admins admins=new Admins("admin","admin");
        Restaurants res=new Restaurants("Res","Loc","zone",admins,men);
        men.setRestaurant(res);
        given(restaurantsService.getById(6)).willReturn(res);
        given(iMenuRepository.save(men)).willReturn(men);
        Menu meni=menuService.saveMenu(men,6);
        assertEquals(men,meni);

    }

}
