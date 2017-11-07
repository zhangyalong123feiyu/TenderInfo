package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-7-27.
 */

public class RatingResultBean implements Serializable {
    private String questionContent;
    private QustionQuerFilter questionQueryFilter;
    private String questionTitle;
    private String answerContent;
    private String answerTime;
    private String resCode;
    private String resMessage;
    private String answerCode;
    private String commentContent;
    private int commentLevel;

    public String getAnswerCode() {
        return answerCode;
    }

    public void setAnswerCode(String answerCode) {
        this.answerCode = answerCode;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public int getCommentLevel() {
        return commentLevel;
    }

    public void setCommentLevel(int commentLevel) {
        this.commentLevel = commentLevel;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public QustionQuerFilter getQuestionQueryFilter() {
        return questionQueryFilter;
    }

    public void setQuestionQueryFilter(QustionQuerFilter questionQueryFilter) {
        this.questionQueryFilter = questionQueryFilter;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

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

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }


    private String headPortrait;
    private class QustionQuerFilter{
        private String objectId;
        private String summary;

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }
    }
}
