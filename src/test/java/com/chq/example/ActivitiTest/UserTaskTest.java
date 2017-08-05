package com.chq.example.ActivitiTest;

import com.chq.example.SpringBootActivitiApplication;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by CHQ on 2017/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootActivitiApplication.class)
public class UserTaskTest {

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
        runtimeService.startProcessInstanceByKey("userTaskProcess");
    }

    /**
     * 根据组查询任务
     */
    @Test
    public void testQueryTaskByGroup() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("group1").list();
        if (null != tasks && tasks.size() > 0) {
            System.out.println(tasks.get(0).getName());
        } else {
            System.out.println("未查询到任务信息");
        }
    }

    /**
     * 根据用户查询任务
     */
    @Test
    public void testQueryTaskByAssignee() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("user1").list();
        if (null != tasks && tasks.size() > 0) {
            System.out.println(tasks.get(0).getName());
        } else {
            System.out.println("未查询到任务信息");
        }
    }

    /**
     * 领取任务
     */
    @Test
    public void testClaimTask() {
        List<Task> tasks = taskService.createTaskQuery().taskCandidateOrAssigned("user1").list();

        if (null != tasks && tasks.size() > 0) {
            Task task = tasks.get(0);
            System.out.println(task.getName());
            taskService.claim(task.getId(), "user1");
            String assignee = task.getAssignee();
            System.out.println(assignee);
        } else {
            System.out.println("未查询到任务信息");
        }
    }

    /**
     * 完成任务
     */
    @Test
    public void testCompleteTask() {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("user1").list();
        if (null != tasks && tasks.size() > 0) {
            Task task = tasks.get(0);
            System.out.println(task.getName());
            taskService.complete(task.getId());
            System.out.println("任务：" + task.getName() + "已经被用户user1完成");
        }
    }
}
