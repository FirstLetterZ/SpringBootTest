package com.zpf.demo.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpf.demo.user.been.UserTimeInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by ZPF on 2019/6/5.
 */
@Repository
public interface UserTimeRepository extends BaseMapper<UserTimeInfo> {
}
