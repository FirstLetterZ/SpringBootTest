package com.zpf.demo.user.repository;

import com.zpf.demo.user.been.UserSaveInfo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ZPF on 2019/6/5.
 */

public interface UserRepository extends JpaRepository<UserSaveInfo,Integer>{
}
