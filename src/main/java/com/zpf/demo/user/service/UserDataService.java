package com.zpf.demo.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zpf.demo.user.been.UserBaseInfo;
import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserTimeInfo;
import com.zpf.demo.user.repository.UserRepository;
import com.zpf.demo.user.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserDataService extends ServiceImpl<UserRepository, UserPersonalInfo> {

    @Autowired
    private RedisUtil redisUtil;
//
//    @Autowired
//    private UserRepository userRepository;

//    @Autowired
//    private UserTimeRepository userTimeRepository;

    public void createNewUser(UserPersonalInfo baseInfo) {
       /* UserTimeInfo timeInfo = new UserTimeInfo();
        timeInfo.setId(baseInfo.getUsrId());
        timeInfo.setCreateTime(new Date());
        timeInfo.setActiveTime(new Date());*/
//        userTimeRepository.insert(timeInfo);
//        redisUtil.set("lastUser", baseInfo);
        baseMapper.insert(baseInfo);
    }

    public UserPersonalInfo findLastUser() {
        UserPersonalInfo info = null;
//        try {
//            info = (UserPersonalInfo) redisUtil.get("lastUser");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if (info != null) {
//            info.setEmail("12345678");
//            return info;
//        }
        List<UserPersonalInfo> users = baseMapper.selectList(null);
        if (users.size() > 0) {
            info = users.get(users.size() - 1);
        } else {
            info = new UserPersonalInfo();
            info.setRealName("test_A");
        }
//        redisUtil.set("lastUser", info);
        return info;
    }
}
