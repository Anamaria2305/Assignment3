package com.example.demo2;
import com.example.demo2.entity.Admins;
import com.example.demo2.entity.Restaurants;
import com.example.demo2.entity.User;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.repository.IUserRepository;
import com.example.demo2.service.AES;
import com.example.demo2.service.AdminsService;
import com.example.demo2.service.UserService;
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
public class UserServiceTest {
    @Mock
    private IUserRepository iUserRepository;
    @InjectMocks
    private UserService userService;
    @Test
    void testAll(){
        List<User> userList=new ArrayList<>();
        User user1=new User("user","pass","add");
        User user2=new User("user2","pass2","add2");
        userList.add(user1);
        userList.add(user2);
        given(iUserRepository.findAll()).willReturn(userList);
        List<User> actual=userService.getAll();
        assertEquals(userList, actual);
    }
    @Test
    void testFindById(){
        User user1=new User("user","pass","add");
        given(iUserRepository.findById(6)).willReturn(Optional.of(user1));
        User act=userService.getById(6);
        assertEquals(user1,act);
    }
    final String secretKey = "ssshhhhhhhhhhh!!!!";
    @Test
    void findUP(){
        List<User> userList=new ArrayList<>();
        User user1=new User("user",AES.encrypt("pass",secretKey),"add");
        User user2=new User("user2",AES.encrypt("pass2",secretKey),"add2");
        userList.add(user1);
        userList.add(user2);
        given(iUserRepository.findAll()).willReturn(userList);
        User actual=userService.findByUsernameAndPass("user","pass");
        assertEquals(user1,actual);
    }
    @Test
    void testSave(){
        User user1=new User("user",AES.encrypt("pass",secretKey),"add");
        given(iUserRepository.save(user1)).willReturn(user1);
        User actual=userService.saveUser(user1);

        assertEquals(user1,actual);
    }
}
