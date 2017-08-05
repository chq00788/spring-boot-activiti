package com.chq.example.controller;

import com.chq.example.entity.Request;
import com.chq.example.entity.Response;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 用户信息
 * Created by CHQ on 2017/8/2.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IdentityService identityService;


    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping("/page")
    public String page() {
        return "user";
    }

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public Response<List<User>> query(Request request) {
        Response<List<User>> response = new Response<>();
        try {
            int firstResult = request.getOffset();
            int maxResults = request.getLimit();
            List<User> list = identityService.createUserQuery().listPage(firstResult, maxResults);
            Long total = identityService.createUserQuery().count();
            response.setTotal(total);
            response.setRows(list);
        } catch (Exception e) {
            response.setSuccess(false);
            log.error("查询用户信息失败! 失败原因：", e.getMessage());
        }
        return response;
    }

    /**
     * 增加用户信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Response<String> add(UserEntity user) {
        Response<String> response = new Response<>();
        try {
            identityService.saveUser(user);
            response.setRows("保存成功");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setRows("保存失败");
            log.error("新增用户信息失败! 失败原因：", e.getMessage());
        }
        return response;
    }

    /**
     * 设置角色
     *
     * @return
     */
    @RequestMapping("/setGroup")
    @ResponseBody
    public Response<String> setGroup(String userId, String groupId) {
        Response<String> response = new Response<>();
        try {
            identityService.createMembership(userId, groupId);
            response.setMessage("保存成功");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("保存失败");
            log.error("保存用户组信息失败! 失败原因：", e.getMessage());
        }
        return response;
    }


    /**
     * 删除用户信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Response<String> delete(String userId) {
        Response<String> response = new Response<>();
        try {
            identityService.deleteUser(userId);
            response.setMessage("删除成功");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setMessage("删除失败");
            log.error("删除用户信息失败 失败原因：", e.getMessage());
        }
        return response;
    }
}
