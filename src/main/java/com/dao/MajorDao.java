package com.dao;

/**
 * Created by MaiBenBen on 2019/5/2.
 */
public class MajorDao {
    private String majorId;
    private String majorName;
    private String userId;
    private String identify;

    public MajorDao(String majorId, String majorName, String userId, String identify) {
        this.majorId = majorId;
        this.majorName = majorName;
        this.userId = userId;
        this.identify = identify;
    }

    public MajorDao(String majorId, String majorName) {
        this.majorId = majorId;
        this.majorName = majorName;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
