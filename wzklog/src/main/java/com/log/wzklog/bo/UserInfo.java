package com.log.wzklog.bo;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
    public UserInfo(){}
    private String userName;
    private String password;
    private Integer id;
    private String phone;
    private String flag = null;
    public void setUserInfo(String userName,String password,Integer id,String phone,String flag){
        this.userName = userName;
        this.password = password;
        this.id = id;
        this.phone = phone;
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
