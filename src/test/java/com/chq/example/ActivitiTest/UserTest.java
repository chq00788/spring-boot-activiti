package com.chq.example.ActivitiTest;

import com.chq.example.SpringBootActivitiApplication;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by CHQ on 2017/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootActivitiApplication.class)
public class UserTest {

    @Autowired
    IdentityService identityService;

    @Test
    public void testNewUser() {
        UserEntity user = new UserEntity();
        user.setPassword("user8");
        user.setEmail("user4@activiti.com");
        user.setFirstName("user8");
        user.setLastName("user8");
        identityService.saveUser(user);
    }

    @Test
    public void testEditUser() {
        User entity = identityService.createUserQuery().userId("user4").singleResult();
        entity.setLastName("user444");
        identityService.saveUser(entity);
    }
}
