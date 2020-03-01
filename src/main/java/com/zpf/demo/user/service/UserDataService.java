package com.zpf.demo.user.service;

import com.zpf.demo.user.been.UserBaseInfo;
import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserTimeInfo;
import com.zpf.demo.user.repository.UserRepository;
import com.zpf.demo.user.repository.UserTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserDataService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserTimeRepository userTimeRepository;

    public void createNewUser(UserBaseInfo baseInfo) {
        UserTimeInfo timeInfo = new UserTimeInfo();
        timeInfo.setId(baseInfo.getId());
        timeInfo.setCreateTime(new Date());
        timeInfo.setActiveTime(new Date());
        userTimeRepository.save(timeInfo);
    }

    public UserPersonalInfo findLastUser() {
        List<UserPersonalInfo> users = userRepository.findAll();
        if (users.size() > 0) {
            return users.get(users.size() - 1);
        } else {
            UserPersonalInfo info = new UserPersonalInfo();
            info.setRealName("test_A");
            return info;
        }
    }
}
