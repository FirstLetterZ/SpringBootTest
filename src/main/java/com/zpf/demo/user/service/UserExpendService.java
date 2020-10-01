package com.zpf.demo.user.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.tools.RedisUtil;
import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.entity.UserExpendEntity;
import com.zpf.demo.user.repository.UserBaseRepository;
import com.zpf.demo.user.repository.UserExpendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserExpendService extends ServiceImpl<UserExpendRepository, UserExpendEntity> {

    public void createNewUser(UserExpendEntity expendEntity) {
        baseMapper.insert(expendEntity);
    }

    public UserExpendEntity findUserExpandInfo(String usrId) {
        return baseMapper.selectById(usrId);
    }
}
