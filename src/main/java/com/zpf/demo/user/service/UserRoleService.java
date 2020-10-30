package com.zpf.demo.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.user.entity.UserRoleEntity;
import com.zpf.demo.user.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends ServiceImpl<UserRoleRepository, UserRoleEntity> {

    public UserRoleEntity findRolePermission(String role) {
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_name", role);
        return baseMapper.selectOne(queryWrapper);
    }

}
