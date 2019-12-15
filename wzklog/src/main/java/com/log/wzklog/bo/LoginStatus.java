package com.log.wzklog.bo;

import org.springframework.stereotype.Component;

@Component
public class LoginStatus {
    private boolean newPW = false;
    private boolean check = false;
    private boolean isOk = false;
    private boolean logon = false;

    public LoginStatus(){}
    public LoginStatus(boolean newPW,boolean check,boolean isOk,boolean logon){
        this.newPW = newPW;
        this.check = check;
        this.isOk = isOk;
        this.logon = logon;
    }

    public boolean isNewPW() {
        return newPW;
    }

    public void setNewPW(boolean newPW) {
        this.newPW = newPW;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public boolean isLogon() {
        return logon;
    }

    public void setLogon(boolean logon) {
        this.logon = logon;
    }
}
