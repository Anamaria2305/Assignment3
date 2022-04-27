package com.example.demo2;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.Food;
import com.example.demo2.entity.Menu;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.ICategoryRepository;
import com.example.demo2.repository.IFoodRepository;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.CategoryService;
import com.example.demo2.service.FoodService;
import com.example.demo2.service.MenuService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class FoodServiceTest {
    @Mock
    private IFoodRepository iFoodRepository;
    @Mock
    private CategoryService categoryService;
    @Mock
    private MenuService menuService;
    @InjectMocks
    private FoodService foodService;

    @Test
    void testAll(){
        List<Food> foodsList=new ArrayList<>();
        Category categ1=new Category("Drinks");
        Category categ2=new Category("Sweets");
        foodsList.add(new Food("Food1","descr1",(float)12.7,categ2));
        foodsList.add(new Food("Food2","descr2",(float)12.7,categ1));
        given(iFoodRepository.findAll()).willReturn(foodsList);
        List<Food> actual=foodService.getAll();
        assertEquals(foodsList, actual);
    }
    @Test
    void saveTest(){

        Category categ1=new Category("Drinks");
        Food existing=new Food("Food2","descr2",(float)12.6,categ1);
        List<Food> ne=new ArrayList<>();
        ne.add(existing);
        categ1.setFoods(ne);
        List<Food> fl=new ArrayList<>();
        fl.add(existing);
        Menu menu=new Menu("Meniu",fl);
        List<Menu> menuList=new ArrayList<>();
        menuList.add(menu);
        Food foodSave=new Food("Food1","descr1",(float)12.7,categ1,menuList);
        given(categoryService.findByName("Drinks")).willReturn(categ1);
        given(menuService.findByName("Meniu")).willReturn(menu);
        when(iFoodRepository.save(any(Food.class))).thenReturn(foodSave);
        Food returned=foodService.saveFood("Drinks",foodSave,"Meniu");
        assertEquals(returned, foodSave);
    }
}
