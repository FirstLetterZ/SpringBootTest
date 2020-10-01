package com.zpf.demo.manager.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpf.demo.user.been.UserPersonalInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by ZPF on 2019/6/5.
 */
@Repository
public interface UserManagerRepository extends BaseMapper<UserPersonalInfo> {
}
