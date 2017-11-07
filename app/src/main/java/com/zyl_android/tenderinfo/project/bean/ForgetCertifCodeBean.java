package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-9-7.
 */

public class ForgetCertifCodeBean implements Serializable {

    /**
     * resCode : 0000
     * resMessage : 操作成功
     * item : true
     */

    private String resCode;
    private String resMessage;
    private boolean item;

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

    public boolean isItem() {
        return item;
    }

    public void setItem(boolean item) {
        this.item = item;
    }
}
