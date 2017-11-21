package com.zyl_android.tenderinfo.project.sql;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by bibinet on 2017-11-21.
 * 该类为测试litepal第三方数据库框架使用创建
 */

public class UserTable extends DataSupport {
    private int userId;
    @Column(nullable = true,defaultValue ="bibi")
    private String userName;
    private String companyName;
    private String address;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
