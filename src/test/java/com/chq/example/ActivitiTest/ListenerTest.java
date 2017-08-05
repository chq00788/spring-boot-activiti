package com.chq.example.ActivitiTest;

import com.chq.example.SpringBootActivitiApplication;
import org.activiti.engine.RuntimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 监听器测试类
 *
 * @author
 * @create 2017-08-03 10:31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootActivitiApplication.class)
public class ListenerTest {
    @Autowired
    RuntimeService runtimeService;

    /**
     * 启动流程
     */
    @Test
    public void testStartProcess() {
        runtimeService.startProcessInstanceByKey("listenerProcess");
    }
}
