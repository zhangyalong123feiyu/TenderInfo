package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-6-26.
 */

public class AskExpertsBean implements Serializable {

    private String resCode;
    private String resMessage;
    private String expertCodeStr;
    private String questionCode;
    private String orderId;

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

    public String getExpertCodeStr() {
        return expertCodeStr;
    }

    public void setExpertCodeStr(String expertCodeStr) {
        this.expertCodeStr = expertCodeStr;
    }

    public String getQuestionCode() {
        return questionCode;
    }

    public void setQuestionCode(String questionCode) {
        this.questionCode = questionCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
