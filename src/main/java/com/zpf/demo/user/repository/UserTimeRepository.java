package com.zpf.demo.user.repository;


import com.zpf.demo.user.been.UserPersonalInfo;
import com.zpf.demo.user.been.UserTimeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZPF on 2019/6/5.
 */

@Repository
public interface UserTimeRepository extends JpaRepository<UserTimeInfo, Long> {
}
