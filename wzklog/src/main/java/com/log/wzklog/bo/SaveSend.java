package com.log.wzklog.bo;

import org.springframework.stereotype.Component;

@Component
public class SaveSend {
    public SaveSend(){};
    private Integer id;
    private String title;
    private String content;
    private String saveTime;
    private String flag;
    public void setSaveSend(Integer id,String title,String content,String saveTime,String flag){
        this.id = id;
        this.title = title;
        this.content = content;
        this.saveTime = saveTime;
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getSaveTime() {
        return saveTime;
    }
    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    @Override
    public String toString() {
        return "SaveSend{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", saveTime='" + saveTime + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
