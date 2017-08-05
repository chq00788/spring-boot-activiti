package com.chq.example.ActivitiTest;

import com.chq.example.SpringBootActivitiApplication;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CHQ on 2017/7/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootActivitiApplication.class)
public class ExclusiveProcessTest {

    @Autowired
    RuntimeService runtimeService;
    @Autowired
    TaskService taskService;
    @Autowired
    IdentityService identityService;


    /**
     * 启动流程
     */
    @Test
    public void testStartProcess() {
        Map<String, Object> map = new HashMap<>();
        map.put("type", 3);
        runtimeService.startProcessInstanceByKey("exclusiveProcess", map);

    }
}
