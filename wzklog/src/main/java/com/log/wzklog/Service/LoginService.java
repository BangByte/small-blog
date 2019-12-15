package com.log.wzklog.Service;

import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.bo.UserInfo;

import java.util.List;

public interface LoginService {
    // 登录 查询 用户
    UserInfo byUserLogin(String username,String password);

    // 创建用户 查询 用户名 和 phone
    List<UserInfo> byNameOrPhone(String username, String email);

    // 创建用户 保存
    Integer saveUser(UserInfo userInfo);

    //忘记密码 - 查询手机号
    Integer upPassword(String phone,String password);

    //忘记密码 - 查询手机号
    UserInfo byPhone(String phone);

    // 保存文章
    Integer saveContent(SaveSend saveSend);

    // 查询文章总数
    List<SaveSend> byCount();

    // 根据id查询
    SaveSend byCountId(Integer id);

    // 根据id删除
    Integer delTextId(Integer id);

    // 根据id修改
    Integer upTextId(Integer id,String title,String flag);
}
