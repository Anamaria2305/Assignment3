package com.example.demo2;

import com.example.demo2.entity.Admins;
import com.example.demo2.repository.IAdminsRepository;
import com.example.demo2.service.AdminsService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @Mock
    private IAdminsRepository iAdminsRepository;
    @InjectMocks
    private AdminsService adminsService;

    @Test
    void testAll(){
        List<Admins> adminsList=new ArrayList<>();
        adminsList.add(new Admins("Test1","1234"));
        adminsList.add(new Admins("Test2","12345678"));
        given(iAdminsRepository.findAll()).willReturn(adminsList);
        List<Admins> expected=adminsService.getAll();
        assertEquals(expected, adminsList);
    }
    @Test
    void testFindById(){
        Admins admin=new Admins("Admin","admin");
        given(iAdminsRepository.findById(6)).willReturn(Optional.of(admin));
        Admins exp=adminsService.getById(6);
        assertEquals(exp,admin);
    }
    @Test
    void testFindByIdNot(){
        Admins adm=new Admins();
        when(iAdminsRepository.findById(6)).thenReturn(Optional.of(adm));
        assertEquals(adminsService.getById(6),adm);
    }
    @Test
    void testFUP(){
        List<Admins> adminsList=new ArrayList<>();
        adminsList.add(new Admins("Test1","1234"));
        adminsList.add(new Admins("Test2","12345678"));
        given(iAdminsRepository.findAll()).willReturn(adminsList);
        Admins expected=adminsService.findByUsernameAndPass("Test1","1234");
        assertEquals(expected,adminsList.get(0));
    }
    @Test
    void testFUPN(){
        List<Admins> adminsList=new ArrayList<>();
        adminsList.add(new Admins("Test1","1234"));
        adminsList.add(new Admins("Test2","12345678"));
        given(iAdminsRepository.findAll()).willReturn(adminsList);
        Admins actual=adminsService.findByUsernameAndPass("Test2","1234");
        assertEquals(null,actual);
    }

}
