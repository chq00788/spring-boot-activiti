package com.chq.example.controller;

import com.chq.example.entity.Response;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
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
 * 用户组信息
 * Created by CHQ on 2017/8/2.
 */
@Controller
@RequestMapping("/group")
public class GroupController {
    private static final Logger log = LoggerFactory.getLogger(GroupController.class);

    @Autowired
    private IdentityService identityService;

    /**
     * 跳转页面
     *
     * @return
     */
    @RequestMapping("/page")
    public String page() {
        return "group";
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping(value = "/query")
    @ResponseBody
    public Response<List<Group>> query() {
        Response<List<Group>> response = new Response<>();
        try {
            List<Group> list = identityService.createGroupQuery().list();
            response.setRows(list);
        } catch (Exception e) {
            response.setSuccess(false);
            log.error("查询组信息失败! 失败原因：", e.getMessage());
        }
        return response;
    }

    /**
     * 增加组信息
     *
     * @param group
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public Response<String> add(GroupEntity group) {
        Response<String> response = new Response<>();
        try {
            identityService.saveGroup(group);
            response.setRows("保存成功");
        } catch (Exception e) {
            response.setSuccess(false);
            response.setRows("保存失败");
            log.error("新增组信息失败! 失败原因：", e.getMessage());
        }
        return response;
    }

    /**
     * 修改
     *
     * @param group
     * @param model
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Model edit(GroupEntity group, Model model) {
        try {
            Group entity = identityService.createGroupQuery().groupId(group.getId()).singleResult();
            if (null == entity) {
                throw new Exception("组信息未找到");
            }
            entity.setName(group.getName());
            entity.setType(group.getType());
            identityService.saveGroup(group);
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("message", "保存失败");
            log.error("编辑组信息失败! 失败原因：", e.getMessage());
        }
        return model;
    }

    /**
     * 删除
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Model delete(String id, Model model) {
        try {
            identityService.deleteGroup(id);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("success", false);
            model.addAttribute("message", "删除失败");
            log.error("删除组信息失败! 失败原因：", e.getMessage());
        }
        return model;
    }
}
