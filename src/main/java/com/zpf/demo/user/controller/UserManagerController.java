package com.zpf.demo.user.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpf.demo.global.ErrorCode;
import com.zpf.demo.global.ResponseBean;
import com.zpf.demo.global.UserPermission;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserRoleEntity;
import com.zpf.demo.user.service.UserBaseService;
import com.zpf.demo.user.service.UserRoleService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserManagerController {

    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private UserBaseService userBaseService;

    @RequestMapping(value = "/queryRoleList", method = RequestMethod.GET)
    public ResponseBean<List<UserRoleEntity>> queryRoleList() {
        List<UserRoleEntity> list = userRoleService.getBaseMapper().selectList(null);
        return new ResponseBean<>(list);
    }

    @RequestMapping(value = "/queryAllPermission", method = RequestMethod.GET)
    public ResponseBean<List<String>> queryAllPermission() {
        List<String> list = new ArrayList<>();
        for (UserPermission p : UserPermission.values()) {
            list.add(p.getName());
        }
        return new ResponseBean<>(list);
    }

    @RequiresPermissions(logical = Logical.OR, value = {"edit", "create"})
    @RequestMapping(value = "/updateRoleInfo", method = RequestMethod.POST)
    public ResponseBean<Boolean> updateRoleInfo(@RequestBody Map<String, Object> params) {
        String roleName = null;
        try {
            roleName = params.get("roleName").toString();
        } catch (Exception e) {
            //
        }
        ResponseBean<Boolean> result = new ResponseBean<>();
        if (roleName == null) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", roleName);
        ArrayList<String> ps = new ArrayList<>();
        try {
            JSONArray pArray = (JSONArray) params.get("permissions");
            if (pArray != null) {
                for (Object po : pArray) {
                    if (po != null) {
                        if (UserPermission.checkPermission(po.toString()) && !ps.contains(po.toString())) {
                            ps.add(po.toString());
                        }
                    }
                }
            }
        } catch (Exception e) {
            //
        }
        UserRoleEntity roleEntity = new UserRoleEntity();
        roleEntity.setRoleName(roleName);
        roleEntity.setPermissions(ps);
        int delResult = userRoleService.getBaseMapper().update(roleEntity, queryWrapper);
        if (delResult <= 0) {
            delResult = userRoleService.getBaseMapper().insert(roleEntity);
        }
        result.data = delResult > 0;
        return result;
    }

    @RequiresPermissions(logical = Logical.OR, value = {"edit", "query"})
    @RequestMapping(value = "/queryUserInRole", method = RequestMethod.GET)
    public ResponseBean<List<UserBaseEntity>> queryUserInRole(@RequestParam("roleName") String role) {
        QueryWrapper<UserBaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", role);
        List<UserBaseEntity> list = userBaseService.getBaseMapper().selectList(queryWrapper);
        return new ResponseBean<>(list);
    }

    @RequiresRoles(value = {"super-admin"})
    @RequestMapping(value = "/delRole", method = RequestMethod.POST)
    public ResponseBean<Boolean> deleteRole(@RequestBody Map<String, Object> params) {
        String roleName = null;
        try {
            roleName = params.get("roleName").toString();
        } catch (Exception e) {
            //
        }
        ResponseBean<Boolean> result = new ResponseBean<>();
        if (roleName == null) {
            result.setErrInfo(ErrorCode.PARAMS_NO_ALLOWED);
            return result;
        }
        int delResult = userRoleService.getBaseMapper().deleteById(roleName);
        if (delResult > 0) {
            result.data = true;
        } else {
            result.setErrInfo(ErrorCode.REMOVE_FAIL);
        }
        return result;
    }


}
