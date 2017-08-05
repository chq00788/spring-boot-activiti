package com.chq.example.controller;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CHQ on 2017/7/21.
 */
@RestController
@RequestMapping("/activiti")
public class ActivitiController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping("/start")
    public String start(Model model) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantName", "John Doe");
        variables.put("email", "john.doe@activiti.com");
        variables.put("phoneNumber", "123456789");
        runtimeService.startProcessInstanceByKey("myProcess", variables);
        model.addAttribute("", "");
        return "success";
    }
}
