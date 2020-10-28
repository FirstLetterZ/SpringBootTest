package com.zpf.demo.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.tools.RedisUtil;
import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserRegisterInfo;
import com.zpf.demo.user.entity.UserBaseEntity;
import com.zpf.demo.user.repository.UserBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserBaseService extends ServiceImpl<UserBaseRepository, UserBaseEntity> {

    public UserBaseEntity findUserBaseInfoByAccount(String account) {
        QueryWrapper<UserBaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account);
        return baseMapper.selectOne(queryWrapper);
    }

    public UserBaseEntity findUserBaseInfoById(String userId) {
        QueryWrapper<UserBaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("usrId", userId);
        return baseMapper.selectOne(queryWrapper);
    }

    public UserBaseEntity createNewUser(UserRegisterInfo registerInfo) {
        UserBaseEntity baseEntity = new UserBaseEntity();
        String newUseId = UUID.randomUUID().toString();
        baseEntity.setUsrId(newUseId);
        baseEntity.setAccount(registerInfo.getAccount());
        baseEntity.setPassword(registerInfo.getPassword());
        baseEntity.setNickname("用户_" + newUseId.substring(0, 5));
        baseEntity.setCreateTime(new Date());
        baseMapper.insert(baseEntity);
        return baseEntity;
    }

    public void deleteUser(String usrId) {
        int result = baseMapper.deleteById(usrId);
        System.out.println("deleteUser==>usrId=" + usrId + ";result=" + result);
    }


}
