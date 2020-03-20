package com.zpf.demo.user.been;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ZPF on 2019/6/5.
 */
@TableName(value = "UserTimeInfo")
public class UserTimeInfo {
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date activeTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date freezingTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date failureTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getFreezingTime() {
        return freezingTime;
    }

    public void setFreezingTime(Date freezingTime) {
        this.freezingTime = freezingTime;
    }

    public Date getFailureTime() {
        return failureTime;
    }

    public void setFailureTime(Date failureTime) {
        this.failureTime = failureTime;
    }
}