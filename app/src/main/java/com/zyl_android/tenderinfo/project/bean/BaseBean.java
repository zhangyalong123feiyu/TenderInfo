package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by Wh on 2017-7-8.
 */

public class BaseBean implements Serializable{
    private String resCode;
    private String resMessage;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }
}
