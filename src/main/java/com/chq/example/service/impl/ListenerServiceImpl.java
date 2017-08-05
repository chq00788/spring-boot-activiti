package com.chq.example.service.impl;

import com.chq.example.service.ListenerService;
import org.springframework.stereotype.Service;

/**
 * 监听器接口实现类
 *
 * @author
 * @create 2017-08-03 10:24
 **/
@Service("listenerService")
public class ListenerServiceImpl implements ListenerService {

    /**
     * 流程开始
     */
    @Override
    public void start() {
        System.out.println("Activiti监听器：流程开始...................................");
    }

    /**
     * 流程结束
     */
    @Override
    public void end() {
        System.out.println("Activiti监听器：流程结束...................................");
    }

    /**
     * 任务开始
     */
    @Override
    public void taskStart() {
        System.out.println("Activiti监听器：任务开始...................................");
    }

    /**
     * 任务结束
     */
    @Override
    public void taskEnd() {
        System.out.println("Activiti监听器：任务结束...................................");
    }

    /**
     * 任务执行
     */
    @Override
    public void taskExecute() {
        System.out.println("Activiti系统任务：任务执行...................................");
    }
}
