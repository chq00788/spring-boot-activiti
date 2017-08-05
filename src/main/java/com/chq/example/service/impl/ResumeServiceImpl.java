package com.chq.example.service.impl;

import com.chq.example.service.ResumeService;
import org.springframework.stereotype.Service;

/**
 * Created by CHQ on 2017/7/21.
 */
@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {


    @Override
    public void storeResume() {
        System.out.println("任务已经执行.....................................");
    }


    @Override
    public void showTask1() {
        System.out.println("#############################执行任务1");
    }

    @Override
    public void showTask2() {
        System.out.println("#############################执行任务2");
    }

    @Override
    public void showTask3() {
        System.out.println("#############################执行任务3");
    }
}
