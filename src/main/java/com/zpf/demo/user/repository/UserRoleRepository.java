package com.zpf.demo.user.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zpf.demo.user.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by ZPF on 2019/6/5.
 */
@Repository
public interface UserRoleRepository extends BaseMapper<UserRoleEntity> {
}
