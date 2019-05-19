package com.dao;

/**
 * Created by MaiBenBen on 2019/5/4.
 */
public class ClassDao {
    private String classId;
    private String className;
    private String teacherId;
    private String majorId;
    private String userId;

    public ClassDao(String classId, String className, String teacherId, String majorId, String userId) {
        this.classId = classId;
        this.className = className;
        this.teacherId = teacherId;
        this.majorId = majorId;
        this.userId = userId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
