package com.chq.example.service;

/**
 * 监听器接口
 *
 * @author
 * @create 2017-08-03 10:23
 **/
public interface ListenerService {
    void start();

    void end();

    void taskStart();

    void taskEnd();

    void taskExecute();
}
