package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-6-23.
 */

public class FoucsedBean implements Serializable {


    private String resCode;
    private String resMessage;
//    private InfoCollectionQueryFilterBean infoCollectionQueryFilter;
    private List<ItemBean> item;

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

//    public InfoCollectionQueryFilterBean getInfoCollectionQueryFilter() {
//        return infoCollectionQueryFilter;
//    }
//
//    public void setInfoCollectionQueryFilter(InfoCollectionQueryFilterBean infoCollectionQueryFilter) {
//        this.infoCollectionQueryFilter = infoCollectionQueryFilter;
//    }

    public List<ItemBean> getItem() {
        return item;
    }

    public void setItem(List<ItemBean> item) {
        this.item = item;
    }

//    public static class InfoCollectionQueryFilterBean {
//        /**
//         * userId : 100761
//         * type : 4
//         */
//
//        private int userId;
//        private int type;
//
//        public int getUserId() {
//            return userId;
//        }
//
//        public void setUserId(int userId) {
//            this.userId = userId;
//        }
//
//        public int getType() {
//            return type;
//        }
//
//        public void setType(int type) {
//            this.type = type;
//        }
//    }

    public static class ItemBean {
        /**
         * projectLocation : 辽宁省
         * projectPublishTime : 发布时间：2017-04-12
         * projectUrl : /pis/biddingInfos/toDetail.htm?code=972a6d75d3984d6991e20423598e3bc7
         * projectTypeName : 服务
         * projectCode : 972a6d75d3984d6991e20423598e3bc7
         * projectAmount :
         * projectType : C
         * projectName : wyq自主录入
         * projectTime :
         * projectDescrp : 打发反反复复反复反复发
         * projectTitle : 招标公告
         */
        private String projectLocation;
        private String projectPublishTime;
        private String projectUrl;
        private String projectTypeName;
        private String projectCode;
        private String projectAmount;
        private String projectType;
        private String projectName;
        private String projectTime;
        private String projectDescrp;
        private String projectTitle;


        private boolean projectIsCollect;  //收藏
        private String projectIsTop;   //是否置顶 0不置顶 1置顶

        //关注类型，详情标题
        private String collectType;
        private String detailName;

        public String getCollectType() {
            return collectType;
        }

        public void setCollectType(String collectType) {
            this.collectType = collectType;
        }

        public String getDetailName() {
            return detailName;
        }

        public void setDetailName(String detailName) {
            this.detailName = detailName;
        }

        public boolean isProjectIsCollect() {
            return projectIsCollect;
        }

        public void setProjectIsCollect(boolean projectIsCollect) {
            this.projectIsCollect = projectIsCollect;
        }

        public String getProjectIsTop() {
            return projectIsTop;
        }

        public void setProjectIsTop(String projectIsTop) {
            this.projectIsTop = projectIsTop;
        }

        public String getProjectLocation() {
            return projectLocation;
        }

        public void setProjectLocation(String projectLocation) {
            this.projectLocation = projectLocation;
        }

        public String getProjectPublishTime() {
            return projectPublishTime;
        }

        public void setProjectPublishTime(String projectPublishTime) {
            this.projectPublishTime = projectPublishTime;
        }

        public String getProjectUrl() {
            return projectUrl;
        }

        public void setProjectUrl(String projectUrl) {
            this.projectUrl = projectUrl;
        }

        public String getProjectTypeName() {
            return projectTypeName;
        }

        public void setProjectTypeName(String projectTypeName) {
            this.projectTypeName = projectTypeName;
        }

        public String getProjectCode() {
            return projectCode;
        }

        public void setProjectCode(String projectCode) {
            this.projectCode = projectCode;
        }

        public String getProjectAmount() {
            return projectAmount;
        }

        public void setProjectAmount(String projectAmount) {
            this.projectAmount = projectAmount;
        }

        public String getProjectType() {
            return projectType;
        }

        public void setProjectType(String projectType) {
            this.projectType = projectType;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getProjectTime() {
            return projectTime;
        }

        public void setProjectTime(String projectTime) {
            this.projectTime = projectTime;
        }

        public String getProjectDescrp() {
            return projectDescrp;
        }

        public void setProjectDescrp(String projectDescrp) {
            this.projectDescrp = projectDescrp;
        }

        public String getProjectTitle() {
            return projectTitle;
        }

        public void setProjectTitle(String projectTitle) {
            this.projectTitle = projectTitle;
        }
    }
}
