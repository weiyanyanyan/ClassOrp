package com.dao;

/**
 * Created by MaiBenBen on 2019/5/3.
 */
public class QuestionInfo {
    private String questionId;
    private String startTime;
    private String endTime;
    private String questionName;

    public QuestionInfo(String questionId, String startTime, String endTime) {
        this.questionId = questionId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public QuestionInfo(String questionId, String startTime, String endTime, String questionName) {
        this.questionId = questionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.questionName = questionName;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
