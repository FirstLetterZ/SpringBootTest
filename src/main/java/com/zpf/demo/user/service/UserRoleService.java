package com.zpf.demo.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.entity.UserRoleEntity;
import com.zpf.demo.user.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserRoleService extends ServiceImpl<UserRoleRepository, UserRoleEntity> {

    public UserRoleEntity findRolePermission(String role) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleName", role);
        return baseMapper.selectOne(queryWrapper);
    }

    public int updateRolePermission(String roleName, String permission, boolean delPermission) {
        UserRoleEntity roleEntity = findRolePermission(roleName);
        if (roleEntity == null) {
            return -1;
        }
        Set<String> pSet = roleEntity.getPermissions();
        if (pSet == null) {
            pSet = new HashSet<>();
        }
        if (delPermission) {
            pSet.remove(permission);
        } else {
            pSet.add(permission);
        }
        roleEntity.setPermissions(pSet);
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("roleName", roleName);
        return baseMapper.update(roleEntity, queryWrapper);
    }

    public int createNewRole(String roleName, List<String> permissions) {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        UserRoleEntity roleEntity = findRolePermission(roleName);
        if (roleEntity == null) {
            roleEntity = new UserRoleEntity();
            roleEntity.setRoleName(roleName);
            Set<String> pSet = new HashSet<>(permissions);
            roleEntity.setPermissions(pSet);
            return baseMapper.insert(roleEntity);
        } else {
            QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("roleName", roleName);
            return baseMapper.update(roleEntity, queryWrapper);
        }
    }

    public int deleteRole(String roleName) {
        return baseMapper.deleteById(roleName);
    }

}
