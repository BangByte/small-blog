package com.log.wzklog.DaoImpl;

import com.log.wzklog.Dao.LoginDao;
import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.bo.UserInfo;
import com.log.wzklog.mapper.UserInfoMapper;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginDaoImpl implements LoginDao {
    public LoginDaoImpl(){}
    private UserInfoMapper userInfoMapper;

    @Autowired
    private LoginDaoImpl(UserInfoMapper userInfoMapper){
        this.userInfoMapper = userInfoMapper;
    }

    @Cacheable(cacheNames = "UserLogin")
    @Override
    public UserInfo byUserLogin(String username, String password) {
        UserInfo userInfo = userInfoMapper.selUser(username,password);
        return userInfo;
    }

    @Cacheable(cacheNames = "NameOrEmail")
    @Override
    public List<UserInfo> byNameOrPhone(String username, String phone) {
        List<UserInfo> i = userInfoMapper.selByNameOrPhone(username, phone);
        return i;
    }

    @Override
    public Integer saveUser(UserInfo userInfo) {
        Integer strId = userInfoMapper.saveUser(userInfo);
        return strId;
    }

    @Override
    public Integer upPassword(String phone,String password) {
        Integer num = userInfoMapper.upPassword(phone,password);
        return num;
    }

    @Override
    public UserInfo byPhone(String phone) {
        UserInfo userInfo = userInfoMapper.selPhone(phone);
        return userInfo;
    }

    @Override
    public Integer saveContent(SaveSend saveSend) {
        Integer contId = userInfoMapper.saveContent(saveSend);
        return contId;
    }

    @Override
    public List<SaveSend> byCount() {
        List<SaveSend> count = userInfoMapper.selCount();
        return count;
    }

    @Override
    public SaveSend byCountId(Integer id) {
        SaveSend saveSend = userInfoMapper.selCountId(id);
        return saveSend;
    }

    @Override
    public Integer delTextId(Integer id) {
        Integer num = userInfoMapper.deleteTextId(id);
        return num;
    }

    @Override
    public Integer upTextId(Integer id,String title,String flag) {
        Integer upId = userInfoMapper.updateTextId(id,title,flag);
        return upId;
    }

}
