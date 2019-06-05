package com.zpf.demo.user.been;

import javax.persistence.MappedSuperclass;

/**
 * Created by ZPF on 2019/6/5.
 */
@MappedSuperclass
public class UserPersonalInfo extends UserBaseInfo {

    private String realName;//

    private String sex;

    private Integer age;

    private String email;

    private String phone;

    private String sdf;//个性签名

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSdf() {
        return sdf;
    }

    public void setSdf(String sdf) {
        this.sdf = sdf;
    }
}
