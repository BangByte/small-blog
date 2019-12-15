package com.log.wzklog.ServiceImpl;

import com.log.wzklog.Dao.LoginDao;
import com.log.wzklog.DaoImpl.LoginDaoImpl;
import com.log.wzklog.Service.LoginService;
import com.log.wzklog.bo.SaveSend;
import com.log.wzklog.bo.UserInfo;
import com.log.wzklog.enumutil.LoginEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoginServiceImpl implements LoginService{
    public LoginServiceImpl(){}
    private LoginDao loginDao;

    @Autowired
    public LoginServiceImpl(LoginDaoImpl loginDaoImpl){
        this.loginDao = loginDaoImpl;
    }
    @Override
    public UserInfo byUserLogin(String username, String password) {
        UserInfo userInfo = loginDao.byUserLogin(username, password);

        if(userInfo != null){
            if(username != null || password != null){
                if(username.equals(userInfo.getUserName()) && password.equals(userInfo.getPassword())){
                    userInfo.setFlag(LoginEnum.SUCCESS.getValue());
                }else{
                    userInfo.setFlag(LoginEnum.LOSE.getValue());
                }
            }else{
                userInfo = new UserInfo();
                userInfo.setUserInfo(null,null,null,null,LoginEnum.LOSE.getValue());
            }
        }else{
            userInfo = new UserInfo();
            userInfo.setUserInfo(null,null,null,null,LoginEnum.LOSE.getValue());
        }
        return userInfo;
    }

    @Override
    public List<UserInfo> byNameOrPhone(String username, String phone) {
        List<UserInfo> i = loginDao.byNameOrPhone(username, phone);
        return i;
    }

    @Override
    public Integer saveUser(UserInfo userInfo) {
        Integer strId = loginDao.saveUser(userInfo);
        return strId;
    }

    @Override
    public Integer upPassword(String phone,String password) {
        Integer num = loginDao.upPassword(phone,password);
        return num;
    }

    @Override
    public UserInfo byPhone(String phone) {
        UserInfo userInfo = loginDao.byPhone(phone);
        return userInfo;
    }

    @Override
    public Integer saveContent(SaveSend saveSend) {
        Integer contId = loginDao.saveContent(saveSend);
        return contId;
    }

    @Override
    public List<SaveSend> byCount() {
        List<SaveSend> count = loginDao.byCount();
        return count;
    }

    @Override
    public SaveSend byCountId(Integer id) {
        SaveSend saveSend = loginDao.byCountId(id);
        return saveSend;
    }

    @Override
    public Integer delTextId(Integer id) {
        Integer num = loginDao.delTextId(id);
        return num;
    }

    @Override
    public Integer upTextId(Integer id,String title,String flag) {
        Integer upId = loginDao.upTextId(id,title,flag);
        return upId;
    }
}
