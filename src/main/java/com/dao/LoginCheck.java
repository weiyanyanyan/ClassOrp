package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by MaiBenBen on 2019/4/21.
 */
public class LoginCheck {
    //登陆审核
    public static Map check(String userId, String password) {
        Map<String, String> userLoginInfoMap = new HashMap<>();

        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user_info where user_id=? and pass_word=?");
            p.setString(1, userId);
            p.setString(2, password);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                String userName = rs.getString("user_name");
                userLoginInfoMap.put("user_name", userName);
                userLoginInfoMap.put("role", role);
                Dao.close(rs, p, conn);
                return userLoginInfoMap;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userLoginInfoMap;
    }

    //获取问卷信息
    public static Map getQuestionData() {
        Map<String, String> map = new HashMap<>();
        try {
            Connection conn = Dao.getConnection();
            String nowTime = String.valueOf(new Date().getTime() / 1000);
            PreparedStatement p = conn.prepareStatement("select * from question_info where start_time<=? and end_time>=?");
            p.setString(1, nowTime);
            p.setString(2, nowTime);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String start_time = rs.getString("start_time");
                String end_time = rs.getString("end_time");
                String question_id = rs.getString("question_id");
                map.put("start_time", start_time);
                map.put("end_time", end_time);
                map.put("question_id", question_id);
                Dao.close(rs, p, conn);
                return map;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取问卷确认状态
    public static Map getQuestionIdentifyStatus(String userId) {
        Map<String, String> map = new HashMap<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from evaluation_info where " +
                    " teacher_id=? and identify_status != 1 ");
            p.setString(1, userId);
            ResultSet rs = p.executeQuery();
            //确认状态获取一个就足够
            if (rs.next()) {
                String question_id = rs.getString("question_id");
                map.put("question_id", question_id);
            }
            Dao.close(rs, p, conn);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取专业信息
    public static List<MajorDao> getMajorData(String userId) {
        List<MajorDao> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select a.major_id,a.major_name,b.identify_status from major_info a join evaluation_info b on a.major_id=b.major_id where a.user_id=? group by a.major_id");
            p.setString(1, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String majorId = rs.getString("major_id");
                String majorName = rs.getString("major_name");
                String identifyStatus = rs.getString("identify_status");
                MajorDao majorDao = new MajorDao(majorId, majorName, userId, identifyStatus);
                majorDao.setUserId(userId);
                list.add(majorDao);
            }
            Dao.close(rs, p, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取问卷信息-admin
    public static List<QuestionInfo> getQuestionnaireData() {
        List<QuestionInfo> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from question_info ");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String questionId = rs.getString("question_id");
                String questionName = rs.getString("question_name");
                String endTime = rs.getString("end_time");
                String startTime = rs.getString("start_time");
                String str = stampToDate(startTime);
                String end = stampToDate(endTime);
                QuestionInfo questionInfo = new QuestionInfo(questionId, str, end, questionName);
                list.add(questionInfo);
            }
            Dao.close(rs, p, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<EvaluationDao> getStudentMainMajorData(String user_id, String question_id) {
        List<EvaluationDao> evalutionList = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from evaluation_info where user_id=? and question_id=?");
            p.setString(1, user_id);
            p.setString(2, question_id);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EvaluationDao evaluationDao = new EvaluationDao();
                String class_name = rs.getString("class_name");
                String class_id = rs.getString("class_id");
                String teacher_id = rs.getString("teacher_id");
                String score = rs.getString("score");
                String process_status = rs.getString("process_status");
                evaluationDao.setClassId(Integer.valueOf(class_id));
                evaluationDao.setClassName(class_name);
                evaluationDao.setScore(score);
                if (process_status.equals("0") || process_status.isEmpty()) {
                    evaluationDao.setProcessStatus(0);
                } else {
                    evaluationDao.setProcessStatus(Integer.parseInt(process_status));
                }
                evaluationDao.setTeacherId(teacher_id);
                evalutionList.add(evaluationDao);
            }
            Dao.close(rs, p, conn);
            return evalutionList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evalutionList;
    }

    //根据问卷id获取问卷信息
    public static Map getQuestionInfo(String question_id) {
        Map<String, String> map = new HashMap<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from question_info where question_id=?");
            p.setString(1, question_id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String questionName = rs.getString("question_name");
                map.put("question_name", questionName);
                Dao.close(rs, p, conn);
                return map;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取个人信息
    public static Map getUserInfo(String user_id) {
        Map<String, String> map = new HashMap<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user_info where user_id=?");
            p.setString(1, user_id);
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                String user_name = rs.getString("user_name");
                map.put("user_name", user_name);
                Dao.close(rs, p, conn);
                return map;
            }
            Dao.close(rs, p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    //获取问卷问题信息
    public static List<String> evaluation(String questionId) {
        List<String> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from question_problem WHERE question_id=? order by id");
            p.setString(1, questionId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String questionDescription = rs.getString("question_description");
                list.add(questionDescription);
            }
            Dao.close(rs, p, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //获取学生课程评教分数
    public static List<StuScoreOfClassDao> getResStuScoreOfClass(String classId,String teacherId) {
        List<StuScoreOfClassDao> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select question_id,score,user_id,class_id,class_name from evaluation_info WHERE class_id=? and teacher_id=? order by id");
            p.setString(1, classId);
            p.setString(2, teacherId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String score = rs.getString("score");
                String userId = rs.getString("user_id");
                String className = rs.getString("class_name");
                String questionId = rs.getString("question_id");
                StuScoreOfClassDao stuScoreOfClassDao = new StuScoreOfClassDao(userId,score,classId,className);
                stuScoreOfClassDao.setQuestionId(questionId);
                list.add(stuScoreOfClassDao);
            }
            Dao.close(rs, p, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    //获取问卷评估结果信息
    public static List<EvaluationResDao> majorEvaluation(String majorId, String userId) {
        List<EvaluationResDao> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from evaluation_info WHERE major_id=? " +
                    " and teacher_id=? GROUP BY id order by identify_status");
            p.setString(1, majorId);
            p.setString(2, userId);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String score = rs.getString("score");
                String teacherId = rs.getString("teacher_id");
                String userStuId = rs.getString("user_id");
                String questionId = rs.getString("question_id");
                String classId = rs.getString("class_id");
                String className = rs.getString("class_name");
                String identifyStatus = rs.getString("identify_status");
                EvaluationResDao evaluationResDao = new EvaluationResDao();
                evaluationResDao.setClassId(classId);
                evaluationResDao.setClassName(className);
                evaluationResDao.setQuestionId(questionId);
                evaluationResDao.setIdentifyStatus(identifyStatus);
                evaluationResDao.setTeacherId(teacherId);
                evaluationResDao.setScore(score);
                evaluationResDao.setUserId(userStuId);
                list.add(evaluationResDao);
            }
            Dao.close(rs, p, conn);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //问卷提交
    public static Boolean submitEvaluation(String resScore, String teacherId,
                                           String classId, String questionId, String userId) {
        Boolean res = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("update evaluation_info set score=? ," +
                    " process_status=1 WHERE question_id=? " +
                    " and class_id=? and teacher_id=? and user_id=? ");
            p.setString(1, resScore);
            p.setString(2, questionId);
            p.setString(3, classId);
            p.setString(4, teacherId);
            p.setString(5, userId);
            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //问卷状态提交
    public static Boolean evaluationIdentifyStatus(String teacherId,
                                                   String classId, String questionId, String userId) {
        Boolean res = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("update evaluation_info set identify_status=1 " +
                    " WHERE question_id=? " +
                    " and class_id=? and teacher_id=? and user_id=? ");
            p.setString(1, questionId);
            p.setString(2, classId);
            p.setString(3, teacherId);
            p.setString(4, userId);
            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //问卷问题提交
    public static Boolean createQuestionProblemAdmin(String questionId, String questionName, String datetimepickerStartTime, String datetimepickerEndTime,
                                                     String questionOne, String questionTwo,
                                                     String questionThree, String questionFour,
                                                     String questionFive, String questionSix,
                                                     String questionSeven, String questionEight,
                                                     String questionNine, String questionTen) throws ParseException {
        Boolean res = false;
        datetimepickerStartTime = dateToStamp(datetimepickerStartTime);
        datetimepickerEndTime = dateToStamp(datetimepickerEndTime);
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("INSERT INTO question_problem (question_description,question_id,question_name) " +
                    " VALUES(?,?,?),(?,?,?),(?,?,?),(?,?,?),(?,?,?)," +
                    "(?,?,?),(?,?,?), (?,?,?),(?,?,?),(?,?,?)");
            p.setString(1, questionOne);
            p.setString(2, questionId);
            p.setString(3, questionName);

            p.setString(4, questionTwo);
            p.setString(5, questionId);
            p.setString(6, questionName);

            p.setString(7, questionThree);
            p.setString(8, questionId);
            p.setString(9, questionName);

            p.setString(10, questionFour);
            p.setString(11, questionId);
            p.setString(12, questionName);

            p.setString(13, questionFive);
            p.setString(14, questionId);
            p.setString(15, questionName);

            p.setString(16, questionSix);
            p.setString(17, questionId);
            p.setString(18, questionName);

            p.setString(19, questionSeven);
            p.setString(20, questionId);
            p.setString(21, questionName);

            p.setString(22, questionEight);
            p.setString(23, questionId);
            p.setString(24, questionName);

            p.setString(25, questionNine);
            p.setString(26, questionId);
            p.setString(27, questionName);

            p.setString(28, questionTen);
            p.setString(29, questionId);
            p.setString(30, questionName);

            p.executeUpdate();
            Dao.closeUpdate(p, conn);

            //question_info
            Connection connQ = Dao.getConnection();
            PreparedStatement pQ = connQ.prepareStatement("INSERT INTO question_info (start_time,end_time,question_id,question_name) " +
                    " VALUES (?,?,?,?)");
            pQ.setString(1, datetimepickerStartTime);
            pQ.setString(2, datetimepickerEndTime);
            pQ.setString(3, questionId);
            pQ.setString(4, questionName);

            pQ.executeUpdate();
            res = true;
            Dao.closeUpdate(pQ, connQ);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //查找至评估表的数据
    public static Boolean createEvaluationInfo(String questionId) {
        Boolean res = false;
        List<ClassDao> list = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select " +
                    "major_id,class_id,class_name,teacher_id,user_id from evaluation_info  where user_id in (select user_id from user_info where role = 1)");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String teacherId = rs.getString("teacher_id");
                String userStuId = rs.getString("user_id");
                String classId = rs.getString("class_id");
                String majorId = rs.getString("major_id");
                String className = rs.getString("class_name");
                ClassDao classDao = new ClassDao(classId, className, teacherId, majorId, userStuId);
                list.add(classDao);
            }
            Dao.closeUpdate(p, conn);
            Boolean insertEvaluationRes = insertEvaluation(list, questionId);
            if (insertEvaluationRes) {
                res = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //插入数据至评估表
    public static Boolean insertEvaluation(List<ClassDao> list, String questionId) {
        Boolean res = false;
        String sql = "insert into evaluation_info (question_id,user_id,teacher_id,class_id,class_name,major_id) ";
        String values = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == 0) {
                values = " values(?,?,?,?,?,?) ";
            } else {
                values += ",(?,?,?,?,?,?) ";
            }
        }
        String resSql = sql + values;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement(resSql);
            int idx = 0;
            for (int i = 0; i < list.size(); i++) {
                p.setString(idx + 1, questionId);
                p.setString(idx + 2, list.get(i).getUserId());
                p.setString(idx + 3, list.get(i).getTeacherId());
                p.setString(idx + 4, list.get(i).getClassId());
                p.setString(idx + 5, list.get(i).getClassName());
                p.setString(idx + 6, list.get(i).getMajorId());
                idx += 6;
            }
            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //将时间戳转换为时间
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (s.length() == 13){
            res = simpleDateFormat.format(Long.parseLong(s));
        }else{
            res = simpleDateFormat.format(Long.parseLong(s)*1000);
        }
        return res;
    }

    //将时间转换为时间戳
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts );
        return res;
    }

    //通过角色获取个人信息
    public static List<UserDao> getUserInfoByRole(String role) {
        List<UserDao> evaluationList = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user_info where role=?");
            p.setString(1, role);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                UserDao userDao = new UserDao();
                String userId = rs.getString("user_id");
                String userName = rs.getString("user_name");
                userDao.setUserId(userId);
                userDao.setUserName(userName);
                userDao.setRole(role);
                evaluationList.add(userDao);
            }
            Dao.close(rs, p, conn);
            return evaluationList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluationList;
    }

    //管理员查看评教信息
    public static List<EvaluationResAllDao> getEvaluationResForAdmin() {
        List<EvaluationResAllDao> evaluationAllList = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select major_id,class_id,class_name," +
                    "teacher_id,avg(score) as score from evaluation_info group by class_id");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                EvaluationResAllDao evaluationResAllDao = new EvaluationResAllDao();
                String majorId = rs.getString("major_id");
                String classId = rs.getString("class_id");
                String teacherId = rs.getString("teacher_id");
                String avgScore = "0";
                if (!(rs.getString("score") == null || rs.getString("score").equals(""))) {
                    DecimalFormat df = new DecimalFormat("#.0");
                    avgScore = df.format(Double.parseDouble(rs.getString("score")));
                }
                String className = rs.getString("class_name");
                evaluationResAllDao.setAvgScore(avgScore);
                evaluationResAllDao.setClassId(classId);
                evaluationResAllDao.setClassName(className);
                evaluationResAllDao.setMajorId(majorId);
                evaluationResAllDao.setTeacherId(teacherId);
                evaluationAllList.add(evaluationResAllDao);
            }
            Dao.close(rs, p, conn);
            return evaluationAllList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return evaluationAllList;
    }

    //管理员查看评教信息--学院信息
    public static List<EvaluationResAllDao> getCollegeInfoResForAdmin(List<EvaluationResAllDao> list) {
        List<CollegeDao> collegeDaos = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select college_id,college_name,major_id from college_info group by major_id");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String majorId = rs.getString("major_id");
                String collegeId = rs.getString("college_id");
                String collegeName = rs.getString("college_name");
                CollegeDao collegeDao = new CollegeDao(collegeId, collegeName, majorId);
                collegeDaos.add(collegeDao);
            }
            Dao.close(rs, p, conn);
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < collegeDaos.size(); j++) {
                    if (list.get(i).getMajorId().equals(collegeDaos.get(j).getMajorId())) {
                        list.get(i).setCollegeName(collegeDaos.get(j).getCollegeName());
                        list.get(i).setCollegeId(collegeDaos.get(j).getCollegeId());
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //管理员查看评教信息--专业信息
    public static List<EvaluationResAllDao> getMajorNameResForAdmin(List<EvaluationResAllDao> list) {
        List<MajorDao> majorDaos = new ArrayList<>();
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select major_name,major_id from major_info ");
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                String majorId = rs.getString("major_id");
                String majorName = rs.getString("major_name");
                MajorDao majorDao = new MajorDao(majorId, majorName);
                majorDaos.add(majorDao);
            }
            Dao.close(rs, p, conn);
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < majorDaos.size(); j++) {
                    if (list.get(i).getMajorId().equals(majorDaos.get(j).getMajorId())) {
                        list.get(i).setMajorName(majorDaos.get(j).getMajorName());
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //管理员查看评教信息--教师信息
    public static List<EvaluationResAllDao> getTeacherNameResForAdmin(List<EvaluationResAllDao> list) {
        List<UserDao> userDaos = new ArrayList<>();
        String role = "2";
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("select * from user_info where role=?");
            p.setString(1, role);
            ResultSet rs = p.executeQuery();
            while (rs.next()) {
                UserDao userDao = new UserDao();
                String userId = rs.getString("user_id");
                String userName = rs.getString("user_name");
                userDao.setUserId(userId);
                userDao.setUserName(userName);
                userDao.setRole(role);
                userDaos.add(userDao);
            }
            Dao.close(rs, p, conn);
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < userDaos.size(); j++) {
                    if (list.get(i).getTeacherId().equals(userDaos.get(j).getUserId())) {
                        list.get(i).setTeacherName(userDaos.get(j).getUserName());
                    }
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //个人信息修改
    public static Boolean personUpdate(String userId,
                                       String userName, String role) {
        Boolean res = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("update user_info set user_name=?,role=? where user_id=? ");
            p.setString(1, userName);
            p.setString(2, role);
            p.setString(3, userId);
            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //个人信息删除
    public static Boolean personDelete(String userId) {
        Boolean res = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("DELETE FROM user_info where user_id=? ");
            p.setString(1, userId);
            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    //个人信息添加
    public static Boolean personAdd(String userId,
                                    String userName, String role, String word) {
        Boolean res = false;
        try {
            Connection conn = Dao.getConnection();
            PreparedStatement p = conn.prepareStatement("insert into  user_info set user_name=?,role=?,user_id=?,pass_word=?");
            p.setString(1, userName);
            p.setString(2, role);
            p.setString(3, userId);
            p.setString(4, word);

            p.executeUpdate();
            res = true;
            Dao.closeUpdate(p, conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
