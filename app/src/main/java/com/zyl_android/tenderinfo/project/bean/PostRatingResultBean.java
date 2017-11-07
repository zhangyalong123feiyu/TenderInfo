package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;

/**
 * Created by bibinet on 2017-7-28.
 */

public class PostRatingResultBean implements Serializable {


    private AppAnswerCommentBean appAnswerComment;
    private String resCode;
    private String resMessage;
    private boolean success;

    public AppAnswerCommentBean getAppAnswerComment() {
        return appAnswerComment;
    }

    public void setAppAnswerComment(AppAnswerCommentBean appAnswerComment) {
        this.appAnswerComment = appAnswerComment;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class AppAnswerCommentBean {
        /**
         * summary :
         * answerCode : 4c97feea601940e78ad9479af6940b88
         * commentLevel : 4
         * commentContent : test
         * creator : 101079
         */

        private String summary;
        private String answerCode;
        private int commentLevel;
        private String commentContent;
        private int creator;

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAnswerCode() {
            return answerCode;
        }

        public void setAnswerCode(String answerCode) {
            this.answerCode = answerCode;
        }

        public int getCommentLevel() {
            return commentLevel;
        }

        public void setCommentLevel(int commentLevel) {
            this.commentLevel = commentLevel;
        }

        public String getCommentContent() {
            return commentContent;
        }

        public void setCommentContent(String commentContent) {
            this.commentContent = commentContent;
        }

        public int getCreator() {
            return creator;
        }

        public void setCreator(int creator) {
            this.creator = creator;
        }
    }
}
