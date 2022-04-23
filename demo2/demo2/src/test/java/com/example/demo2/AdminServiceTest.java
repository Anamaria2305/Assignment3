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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

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

}
