package com.log.wzklog.Dao;

import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.bo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;


public interface LoginDao {
    // 登录 查询 用户
    UserInfo byUserLogin(String username,String password);

    // 创建用户 查询 用户名 和 phone
    List<UserInfo> byNameOrPhone(String username, String phone);

    // 创建用户 保存
    Integer saveUser(UserInfo userInfo);

    //忘记密码 - 修改密码
    Integer upPassword(@Param("phone") String phone,
                       @Param("password") String password);

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
    Integer upTextId(@Param("id") Integer id,@Param("title") String title,
                     @Param("flag") String flag);
}
