package com.dao;

/**
 * Created by MaiBenBen on 2019/5/2.
 */
public class EvaluationResDao {
    private String questionId;
    private String userId;
    private String majorId;
    private String classId;
    private String className;
    private String score;
    private String teacherId;
    private String identifyStatus;
    private String identifyDes;

    public String getIdentifyDes() {
        return identifyDes;
    }

    public void setIdentifyDes(String identifyDes) {
        this.identifyDes = identifyDes;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMajorId() {
        return majorId;
    }

    public void setMajorId(String majorId) {
        this.majorId = majorId;
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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getIdentifyStatus() {
        return identifyStatus;
    }

    public void setIdentifyStatus(String identifyStatus) {
        this.identifyStatus = identifyStatus;
    }
}
