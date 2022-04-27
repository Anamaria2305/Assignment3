package com.example.demo2;
import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.Food;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.ICategoryRepository;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.CategoryService;
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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock
    private ICategoryRepository iCategoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void testAll(){
        List<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category("Categoria1"));
        categoryList.add(new Category("Categoria2"));
        given(iCategoryRepository.findAll()).willReturn(categoryList);
        List<Category> actual=categoryService.getAll();
        assertEquals(categoryList, actual);
    }
    @Test
    void testFN(){
        List<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category("Categoria1"));
        categoryList.add(new Category("Categoria2"));
        given(iCategoryRepository.findAll()).willReturn(categoryList);
        Category actual=categoryService.findByName("Categoria2");
        assertEquals(categoryList.get(1),actual);
    }
    @Test
    void testFNN(){
        List<Category> categoryList=new ArrayList<>();
        categoryList.add(new Category("Categoria1",new ArrayList<>()));
        categoryList.add(new Category("Categoria2",new ArrayList<>()));
        given(iCategoryRepository.findAll()).willReturn(categoryList);
        Category actual=categoryService.findByName("Categoria3");
        assertEquals(null,actual);
    }
    @Test
    void save(){
        Category cat=new Category("Drinks");
        given(iCategoryRepository.save(cat)).willReturn(cat);
        Category actual=categoryService.saveCategory(cat);

        assertEquals(cat,actual);
    }
}
