package com.chq.example.ActivitiTest;

import com.chq.example.SpringBootActivitiApplication;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by CHQ on 2017/7/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootActivitiApplication.class)
public class ActivitiTest {
    @Autowired
    IdentityService identityService;


    @Test
    public void testAddUser() {
        User user = identityService.newUser("user1");
        user.setFirstName("tom");
        user.setLastName("hear");
        user.setPassword("111111");
        user.setEmail("tom@qq.com");
        identityService.saveUser(user);
    }



    @Test
    public void testQueryUser() {
        User user = identityService.createUserQuery().userId("user1").singleResult();
        Assert.assertNotNull(user);
    }

    @Test
    public void testAddGroup() {
        Group group = identityService.newGroup("group1");
        group.setName("dev");
        group.setType("aaa");
        identityService.saveGroup(group);
    }

    @Test
    public void testQueryGroup() {
        Group group = identityService.createGroupQuery().groupId("group1").singleResult();
        Assert.assertNotNull(group);
    }

    @Test
    public void testAddMembership() {
        identityService.createMembership("user1", "group1");
    }

    @Test
    public void testQueryMembership() {
        //查询属于组group1的用户
        List<User> usersInGroup = identityService.createUserQuery().memberOfGroup("group1").list();
        Assert.assertNotEquals(0, usersInGroup.size());
        List<Group> groupsForUser = identityService.createGroupQuery().groupMember("user1").list();
        Assert.assertNotEquals(0, groupsForUser.size());
    }
}
