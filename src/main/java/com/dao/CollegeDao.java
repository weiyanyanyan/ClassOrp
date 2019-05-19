package com.dao;

/**
 * Created by MaiBenBen on 2019/5/6.
 */
public class CollegeDao {
    private String collegeId;
    private String collegeName;
    private String majorId;

    public CollegeDao(String collegeId, String collegeName, String majorId) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.majorId = majorId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }
}
