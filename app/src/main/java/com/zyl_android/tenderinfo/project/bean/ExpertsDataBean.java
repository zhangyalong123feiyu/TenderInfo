package com.zyl_android.tenderinfo.project.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by bibinet on 2017-7-21.
 */

public class ExpertsDataBean implements Serializable {

    private String resCode;
    private KnowledgeExpertQueryFilterBean knowledgeExpertQueryFilter;
    private String resMessage;
    private PageBeanX page;
    private List<ItemsBean> items;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public KnowledgeExpertQueryFilterBean getKnowledgeExpertQueryFilter() {
        return knowledgeExpertQueryFilter;
    }

    public void setKnowledgeExpertQueryFilter(KnowledgeExpertQueryFilterBean knowledgeExpertQueryFilter) {
        this.knowledgeExpertQueryFilter = knowledgeExpertQueryFilter;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public PageBeanX getPage() {
        return page;
    }

    public void setPage(PageBeanX page) {
        this.page = page;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class KnowledgeExpertQueryFilterBean {

        private PageBean page;

        public PageBean getPage() {
            return page;
        }

        public void setPage(PageBean page) {
            this.page = page;
        }

        public static class PageBean {

            private int pageNum;
            private int pageSize;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }
        }
    }

    public static class PageBeanX {
        /**
         * pageNum : 1
         * pageSize : 8
         */

        private int pageNum;
        private int pageSize;

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }
    }

    public static class ItemsBean {

        private String title;
        private String price;
        private String description;
        private String code;
        private String headPortrait;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }
    }
}
