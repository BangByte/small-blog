package com.log.wzklog.mapper;

import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.bo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserInfoMapper {
    // 登录 查询
    UserInfo selUser(String username,String password);
    // 创建 用户 查询 username， phone
    List<UserInfo> selByNameOrPhone(String username,String phone);
    // 创建用户 保存
    Integer saveUser(UserInfo userInfo);

    //忘记密码 - 查询手机号
    Integer upPassword(String phone,String password);
    //忘记密码 - 查询手机号
    UserInfo selPhone(String phone);

    // 保存文章
    Integer saveContent(SaveSend saveSend);
    // 查询文章总数
    List<SaveSend> selCount();
    // 根据id查询
    SaveSend selCountId(Integer id);
    // 根据id删除
    Integer deleteTextId(Integer id);
    // 根据id修改
    Integer updateTextId(Integer id,String title,String flag);
}
