package com.example.demo2;
import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Category;
import com.example.demo2.entity.Menu;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.IRestaurantsRepository;
import com.example.demo2.service.AdminsService;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class RestaurantServiceTest {
    @Mock
    private IRestaurantsRepository iRestaurantsRepository;
    @Mock
    private AdminsService adminsService;
    @InjectMocks
    private RestaurantsService restaurantsService;

    @Test
    void testAll(){
        List<Restaurants> restaurantsList=new ArrayList<>();
        Admins admins=new Admins("admin","admin");
        Admins admins2=new Admins("admin2","admin2");
        restaurantsList.add(new Restaurants("Res","Loc","zone",admins));
        restaurantsList.add(new Restaurants("Res2","Loc2","zone2",admins2));
        given(iRestaurantsRepository.findAll()).willReturn(restaurantsList);
        List<Restaurants> actual=restaurantsService.getAll();
        assertEquals(restaurantsList, actual);
    }
    @Test
    void testFN(){
        List<Restaurants> restaurantsList=new ArrayList<>();
        Admins admins=new Admins("admin","admin");
        Admins admins2=new Admins("admin2","admin2");
        restaurantsList.add(new Restaurants("Res","Loc","zone",admins));
        restaurantsList.add(new Restaurants("Res2","Loc2","zone2",admins2));
        given(iRestaurantsRepository.findAll()).willReturn(restaurantsList);
        Restaurants actual=restaurantsService.findByName("Res2");
        assertEquals(restaurantsList.get(1),actual);
    }
    @Test
    void testFNN(){
        List<Restaurants> restaurantsList=new ArrayList<>();
        Admins admins=new Admins("admin","admin");
        Admins admins2=new Admins("admin2","admin2");
        restaurantsList.add(new Restaurants("Res","Loc","zone",admins));
        restaurantsList.add(new Restaurants("Res2","Loc2","zone2",admins2));
        given(iRestaurantsRepository.findAll()).willReturn(restaurantsList);
        Restaurants actual=restaurantsService.findByName("Categoria4");
        assertEquals(null,actual);
    }
    @Test
    void testFindById(){
        Admins admin=new Admins("Admin","admin");
        Restaurants res=new Restaurants("Res","Loc","zone",admin);
        given(iRestaurantsRepository.findById(6)).willReturn(Optional.of(res));
        Restaurants act=restaurantsService.getById(6);
        assertEquals(res,act);
    }
    @Test
    void testSave(){
        Admins admins=new Admins("admin","admin");
        Restaurants res=new Restaurants("Res","Loc","zone");
        given(adminsService.getById(1)).willReturn(admins);
        res.setAdmin(admins);
        given(iRestaurantsRepository.save(res)).willReturn(res);
        Restaurants rest=restaurantsService.saveRestaurant(res,1);
        assertEquals(res,rest);
    }
}
