package com.dao;

/**
 * Created by MaiBenBen on 2019/5/9.
 */
public class StuScoreOfClassDao {
    private String studentName;
    private String studentId;
    private String score;
    private String classId;
    private String className;
    private String questionId;
    private String questionName;

    public StuScoreOfClassDao(String studentId, String score, String classId, String className) {
        this.studentId = studentId;
        this.score = score;
        this.classId = classId;
        this.className = className;
    }

    public StuScoreOfClassDao(String studentName, String studentId, String score, String classId, String className) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.score = score;
        this.classId = classId;
        this.className = className;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }
}
