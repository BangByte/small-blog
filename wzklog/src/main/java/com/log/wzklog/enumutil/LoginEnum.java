package com.log.wzklog.enumutil;

public enum LoginEnum {
    SUCCESS("success"),LOSE("lose");
    private String value;
    private LoginEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
