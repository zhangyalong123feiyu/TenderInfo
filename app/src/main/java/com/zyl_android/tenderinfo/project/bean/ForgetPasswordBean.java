package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-10-19.
 */

public class ForgetPasswordBean implements Serializable {

    private String resCode;
    private String resMessage;
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
