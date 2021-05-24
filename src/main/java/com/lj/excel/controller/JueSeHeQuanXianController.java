package com.lj.excel.controller;

import com.lj.excel.model.AllPermission;
import com.lj.excel.util.PermissionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*角色和权限的一个测试*/
@RestController
@RequestMapping("/api")
public class JueSeHeQuanXianController {


    /**
     * 分配角色权限 进入页面时（相当于查看详情）
     *
     * @param roleid 角色ID
     * @return
     */
    @RequestMapping("/getPermissions")
    public List<AllPermission> getPermissions(Integer roleid) {

        List<AllPermission> allList = PermissionUtil.getAllPermission();

        return allList;
    }




}
