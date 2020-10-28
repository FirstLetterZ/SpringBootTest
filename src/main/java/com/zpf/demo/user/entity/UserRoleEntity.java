package com.zpf.demo.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zpf.demo.global.handler.StringListTypeHandler;

import java.util.Set;

@TableName(value = "user_role")
public class UserRoleEntity {
    @TableId(type = IdType.INPUT)
    private String roleName = null;
    @TableField(typeHandler = StringListTypeHandler.class)
    private Set<String> permissions;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    public boolean isLegal() {
        return roleName != null && roleName.length() > 0 && permissions != null && permissions.size() > 0;
    }
}
