package com.controller;

import com.dao.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mysql.jdbc.StringUtils.getBytes;

/**
 * Created by MaiBenBen on 2019/4/13.
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    //初始界面
    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    //系统介绍界面
    @RequestMapping("/aboutSystem")
    public String aboutSystem() {
        return "aboutSystem";
    }
    //查看评教结果-admin
    @RequestMapping("/teacherEvaluationRes")
    public String teacherEvaluationRes(Model model,HttpServletRequest request) {
        List<EvaluationResAllDao> evaluationResList = LoginCheck.getEvaluationResForAdmin();
        List<EvaluationResAllDao> evaluationResListAddCollegeInfo = LoginCheck.getCollegeInfoResForAdmin(evaluationResList);
        List<EvaluationResAllDao> evaluationResListAddMajorName = LoginCheck.getMajorNameResForAdmin(evaluationResListAddCollegeInfo);
        List<EvaluationResAllDao> evaluationResListAddTeacherName = LoginCheck.getTeacherNameResForAdmin(evaluationResListAddMajorName);
        model.addAttribute("userInfoList",evaluationResList);
        return "teacherEvaluationRes";
    }

    //查看评教课程折线图结果-admin
    @RequestMapping("/lineEvaluationRes")
    public String lineEvaluationRes(Model model,HttpServletRequest request) {
        String classId = request.getParameter("classId");
        String teacherId = request.getParameter("teacherId");
        List<StuScoreOfClassDao> stuScoreOfClassDaos = LoginCheck.getResStuScoreOfClass(classId,teacherId);
        for (int i=0 ;i< stuScoreOfClassDaos.size();i++) {
            String studentIdShow = String.valueOf(stuScoreOfClassDaos.get(i).getStudentId());
            Map userInfoMap = LoginCheck.getUserInfo(studentIdShow);
            String stuName = String.valueOf(userInfoMap.get("user_name"));
            stuScoreOfClassDaos.get(i).setStudentName(stuName);
            String questionIdShow = String.valueOf(stuScoreOfClassDaos.get(i).getQuestionId());
            Map questionInfoMap = LoginCheck.getQuestionInfo(questionIdShow);
            String questionName = String.valueOf(questionInfoMap.get("question_name"));
            stuScoreOfClassDaos.get(i).setQuestionName(questionName);
        }
        model.addAttribute("list",stuScoreOfClassDaos);
        model.addAttribute("className",stuScoreOfClassDaos.get(1).getClassName());
        return "line_evaluation_res";
    }
    //帮助文档界面
    @RequestMapping("/help")
    public String help() {
        return "help";
    }
    @RequestMapping(value = "/evaluation", method = RequestMethod.GET)
    public String evaluation(Model model,HttpServletRequest request) {
        String classId = request.getParameter("class_id");
        String questionId = request.getParameter("question_id");
        String teacherId = request.getParameter("teacher_id");
        String userId = request.getParameter("user_id");
        String param = request.getQueryString();
        List questionDesList = LoginCheck.evaluation(questionId);
        model.addAttribute("questionDesList",questionDesList);
        model.addAttribute("teacherId",teacherId);
        model.addAttribute("questionId",questionId);
        model.addAttribute("classId",classId);
        model.addAttribute("userId",userId);
        return "evaluation";
    }
    //根据专业查评教结果
    @RequestMapping(value = "/majorEvaluation", method = RequestMethod.GET)
    public String majorEvaluation(Model model,HttpServletRequest request) {
        String majorId = request.getParameter("major_id");
        String teacherId = request.getParameter("user_id");
        String param = request.getQueryString();

        List<EvaluationResDao> evaluationResList = LoginCheck.majorEvaluation(majorId,teacherId);
        for(int i = 0;i<evaluationResList.size();i++){
            EvaluationResDao evaluationResDao = evaluationResList.get(i);
            if("1".equals(evaluationResDao.getIdentifyStatus())){
                evaluationResDao.setIdentifyDes("已确认");
            }else{
                evaluationResDao.setIdentifyDes("未确认");
            }
        }
        model.addAttribute("questionDesList",evaluationResList);
        return "evaluation_teacher";
    }
    //根据问卷id查看问卷内容
    @RequestMapping(value = "/seeQuestionProblem", method = RequestMethod.GET)
    public String seeQuestionProblem(Model model,HttpServletRequest request) {
        String questionId = request.getParameter("question_id");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String param= request.getQueryString();

        List<String> evaluationResList = LoginCheck.evaluation(questionId);
        model.addAttribute("questionDesList",evaluationResList);
        model.addAttribute("startTime",startTime);
        model.addAttribute("endTime",endTime);
        return "see_evaluation_problem";
    }
    //确认问卷状态-确认
    @RequestMapping(value = "/evaluationIdentifyStatus", method = RequestMethod.GET)
    public String evaluationIdentifyStatus(Model model,HttpServletRequest request) {
        String param = request.getQueryString();
        String teacherId = request.getParameter("teacher_id");
        String questionId = request.getParameter("question_id");
        String classId = request.getParameter("class_id");
        String userId = request.getParameter("user_id");
            Boolean aBoolean = LoginCheck.evaluationIdentifyStatus(teacherId,
                    classId,questionId,userId);
            if(aBoolean){
                Map userInfoMap = LoginCheck.getUserInfo(teacherId);
                String teacherName = String.valueOf(userInfoMap.get("user_name"));
                UserDao userLoginTeacher = new UserDao();
                userLoginTeacher.setUserName(teacherName);
                userLoginTeacher.setUserId(teacherId);
                //登陆者信息
                model.addAttribute("userLoginInfo",userLoginTeacher);
                //有无任务确认状态
                String processLabeTeacher = "有任务待确认";
                Map questionInfoMapTeacher = LoginCheck.getQuestionIdentifyStatus(teacherId);
//                String question_id_teacher = String.valueOf(questionInfoMapTeacher.get("question_id"));
                if(questionInfoMapTeacher.size()<=0){
                    processLabeTeacher = "当前无任务！";
                }
                //获取专业信息
                List<MajorDao> majorList = LoginCheck.getMajorData(teacherId);
                List<List<MajorDao>> majorInfoLists= UtilOrp.averageAssign(majorList,2);
                model.addAttribute("process",processLabeTeacher);
                model.addAttribute("majorInfoOne",majorInfoLists.get(0));
                if(majorInfoLists.size() > 1) {
                    model.addAttribute("majorInfoTwo", majorInfoLists.get(1));
                }
                return "main_teacher";
            }else{
                return "error";
            }
    }
    //确认问卷状态-关闭
    @RequestMapping(value = "/evaluationIdentifyStatusClose", method = RequestMethod.GET)
    public String evaluationIdentifyStatusClose(Model model,HttpServletRequest request) {
        String param = request.getQueryString();
        String teacherId = request.getParameter("teacher_id");
            Map userInfoMap = LoginCheck.getUserInfo(teacherId);
            String teacherName = String.valueOf(userInfoMap.get("user_name"));
            UserDao userLoginTeacher = new UserDao();
            userLoginTeacher.setUserName(teacherName);
            userLoginTeacher.setUserId(teacherId);
            //登陆者信息
            model.addAttribute("userLoginInfo",userLoginTeacher);
            //有无任务确认状态
            String processLabeTeacher = "有任务待确认";
            Map questionInfoMapTeacher = LoginCheck.getQuestionIdentifyStatus(teacherId);
            if(questionInfoMapTeacher.size()<=0){
                processLabeTeacher = "无任务需确认！";
            }
            //获取专业信息
            List<MajorDao> majorList = LoginCheck.getMajorData(teacherId);
            List<List<MajorDao>> majorInfoLists= UtilOrp.averageAssign(majorList,2);
            model.addAttribute("process",processLabeTeacher);
            model.addAttribute("majorInfoOne",majorInfoLists.get(0));
            if(majorInfoLists.size() > 1) {
                model.addAttribute("majorInfoTwo", majorInfoLists.get(1));
            }
            return "main_teacher";
    }
    //提交问卷
    @RequestMapping(value = "/submitEvaluation", method = RequestMethod.POST)
    public String submitEvaluation(Model model,HttpServletRequest request) {
        String param = request.getQueryString();
        String teacherId = request.getParameter("teacher_id");
        String questionId = request.getParameter("question_id");
        String classId = request.getParameter("class_id");
        String userId = request.getParameter("user_id");
        int resOne = Integer.parseInt(request.getParameter("1"));
        int resTwo = Integer.parseInt(request.getParameter("2"));
        int resThree = Integer.parseInt(request.getParameter("3"));
        int resFour = Integer.parseInt(request.getParameter("4"));
        int resFive = Integer.parseInt(request.getParameter("5"));
        int resSix = Integer.parseInt(request.getParameter("6"));
        int resSeven = Integer.parseInt(request.getParameter("7"));
        int resEight = Integer.parseInt(request.getParameter("8"));
        int resNine = Integer.parseInt(request.getParameter("9"));
        int resTen = Integer.parseInt(request.getParameter("10"));
        String resScore = String.valueOf(resOne+resTwo+resThree+resFour+resFive+resSix+resSeven+resEight+resNine+resTen);
        Boolean res = LoginCheck.submitEvaluation(resScore,teacherId,classId,questionId,userId);
        if(res){
            //获取登陆者信息
            Map userInfoMapForLogin = LoginCheck.getUserInfo(userId);
            String userName = String.valueOf(userInfoMapForLogin.get("user_name"));
            UserDao userLogin = new UserDao();
            userLogin.setUserName(userName);
            userLogin.setUserId(userId);
            model.addAttribute("userLoginInfo",userLogin);
            Map questionInfoMap = LoginCheck.getQuestionData();
            String question_id = String.valueOf(questionInfoMap.get("question_id"));
            String processLabel = "无任务";
            int processOfEvaluation = 0;
            List<EvaluationDao> majorInfoList = null;
            if(!question_id.isEmpty()){
                majorInfoList = LoginCheck.getStudentMainMajorData(userId,question_id);
                for (int i=0 ;i< majorInfoList.size();i++) {
                    String teacherIdShow = String.valueOf(majorInfoList.get(i).getTeacherId());
                    Map userInfoMap = LoginCheck.getUserInfo(teacherIdShow);
                    String teacherName = String.valueOf(userInfoMap.get("user_name"));
                    majorInfoList.get(i).setTeacherName(teacherName);
                    majorInfoList.get(i).setQuestionId(Integer.valueOf(question_id));
                    int processStatus = majorInfoList.get(i).getProcessStatus();
                    if( processStatus == 1){
                        processOfEvaluation++;
                    }
                }
                int processRes = (int)((new BigDecimal((float) processOfEvaluation/majorInfoList.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
                processLabel = "任务当前进度："+ String.valueOf(processRes)+"%";
            }
            List<List<EvaluationDao>> classInfoLists= UtilOrp.averageAssign(majorInfoList,2);
            model.addAttribute("process",processLabel);
            model.addAttribute("classInfoOne",classInfoLists.get(0));
            if(classInfoLists.size() > 1) {
                model.addAttribute("classInfoTwo", classInfoLists.get(1));
            }
            return "main_student";
        }else {
            return "error";
        }
    }
    //创建问卷跳转页
    @RequestMapping(value = "/createQuestionProblem", method = RequestMethod.GET)
    public String createQuestionProblem(Model model,HttpServletRequest request) {
        String userId = request.getParameter("userId");
        model.addAttribute("userId",userId);
        return "question_create";
    }
    //提交问卷问题-admin
    @RequestMapping(value = "/createQuestionProblemAdmin", method = RequestMethod.POST)
    public String createQuestionProblemAdmin(Model model,HttpServletRequest request) throws ParseException {
        String userId = request.getParameter("userId");
        String datetimepickerStartTime = request.getParameter("datetimepickerStartTime");
        String datetimepickerEndTime = request.getParameter("datetimepickerEndTime");
        String questionId = request.getParameter("questionId");
        String questionName = request.getParameter("questionName");
        String questionOne = request.getParameter("questionOne");
        String questionTwo = request.getParameter("questionTwo");
        String questionThree = request.getParameter("questionThree");
        String questionFour = request.getParameter("questionFour");
        String questionFive = request.getParameter("questionFive");
        String questionSix = request.getParameter("questionSix");
        String questionSeven = request.getParameter("questionSeven");
        String questionEight = request.getParameter("questionEight");
        String questionNine = request.getParameter("questionNine");
        String questionTen = request.getParameter("questionTen");
        Boolean res = LoginCheck.createQuestionProblemAdmin(questionId,questionName,datetimepickerStartTime,datetimepickerEndTime,
                questionOne,questionTwo,questionThree,
                questionFour,questionFive,questionSix,questionSeven,
                questionEight,questionNine,questionTen);
        Boolean resEvaluation = LoginCheck.createEvaluationInfo(questionId);
        if(res && resEvaluation){
            Map userLoginMap = LoginCheck.getUserInfo(userId);
            String userNameAdmin = String.valueOf(userLoginMap.get("user_name"));
            UserDao userLoginAdmin = new UserDao();
            userLoginAdmin.setUserName(userNameAdmin);
            userLoginAdmin.setUserId(userId);
            //登陆者信息
            model.addAttribute("userLoginInfo",userLoginAdmin);
            //获取问卷信息
            List<QuestionInfo> questionInfoList = LoginCheck.getQuestionnaireData();
            List<List<QuestionInfo>> questionInfos= UtilOrp.averageAssign(questionInfoList,2);
            model.addAttribute("questionInfoOne",questionInfos.get(0));
            if(questionInfos.size() > 1) {
                model.addAttribute("questionInfoTwo", questionInfos.get(1));
            }
            return "main_admin";
        }else {
            return "error";
        }
    }
    //根据登陆角色跳转不同主页
    @RequestMapping(value = "/checks", method = RequestMethod.POST)
    public String login(Model model, // 向前台页面传的值放入model中
                        HttpServletRequest request) { // 从前台页面取得的值
        String username = request.getParameter("user_id");
        String password = request.getParameter("password");

        Map userLoginMap = LoginCheck.check(username, password);
        String role = String.valueOf(userLoginMap.get("role"));

        switch (role) {
            case "1":
                String userName = String.valueOf(userLoginMap.get("user_name"));
                UserDao userLogin = new UserDao();
                userLogin.setUserName(userName);
                userLogin.setUserId(username);
                model.addAttribute("userLoginInfo",userLogin);
                Map questionInfoMap = LoginCheck.getQuestionData();
                String question_id = String.valueOf(questionInfoMap.get("question_id"));
                String processLabel = "无任务";
                int processOfEvaluation = 0;
                List<EvaluationDao> majorInfoList = null;
                if(!question_id.isEmpty()){
                    majorInfoList = LoginCheck.getStudentMainMajorData(username,question_id);
                    for (int i=0 ;i< majorInfoList.size();i++) {
                        String teacherId = String.valueOf(majorInfoList.get(i).getTeacherId());
                        Map userInfoMap = LoginCheck.getUserInfo(teacherId);
                        String teacherName = String.valueOf(userInfoMap.get("user_name"));
                        majorInfoList.get(i).setTeacherName(teacherName);
                        majorInfoList.get(i).setQuestionId(Integer.valueOf(question_id));
                        int processStatus = majorInfoList.get(i).getProcessStatus();
                        if( processStatus == 1){
                            processOfEvaluation++;
                        }
                    }
                    int processRes = (int)((new BigDecimal((float) processOfEvaluation/majorInfoList.size()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue())*100);
                    processLabel = "任务当前进度："+ String.valueOf(processRes)+"%";
                }

                List<List<EvaluationDao>> classInfoLists= UtilOrp.averageAssign(majorInfoList,2);
                model.addAttribute("process",processLabel);
                model.addAttribute("classInfoOne",classInfoLists.get(0));
                if(classInfoLists.size() > 1) {
                    model.addAttribute("classInfoTwo", classInfoLists.get(1));
                }
                return "main_student";
            case "2":
                String userNameTeacher = String.valueOf(userLoginMap.get("user_name"));
                UserDao userLoginTeacher = new UserDao();
                userLoginTeacher.setUserName(userNameTeacher);
                userLoginTeacher.setUserId(username);
               //登陆者信息
                model.addAttribute("userLoginInfo",userLoginTeacher);
                //有无任务确认状态
                String processLabeTeacher = "有任务待确认";
                Map questionInfoMapTeacher = LoginCheck.getQuestionIdentifyStatus(username);
                if(questionInfoMapTeacher.size()<=0){
                    processLabeTeacher = "无任务需确认！";
                }
                //获取专业信息
                List<MajorDao> majorList = LoginCheck.getMajorData(username);
                List<List<MajorDao>> majorInfoLists= UtilOrp.averageAssign(majorList,2);
                model.addAttribute("process",processLabeTeacher);
                model.addAttribute("majorInfoOne",majorInfoLists.get(0));
                if(majorInfoLists.size() > 1) {
                    model.addAttribute("majorInfoTwo", majorInfoLists.get(1));
                }
                return "main_teacher";
            case "3":
                String userNameAdmin = String.valueOf(userLoginMap.get("user_name"));
                UserDao userLoginAdmin = new UserDao();
                userLoginAdmin.setUserName(userNameAdmin);
                userLoginAdmin.setUserId(username);
                //登陆者信息
                model.addAttribute("userLoginInfo",userLoginAdmin);
                //获取问卷信息
                List<QuestionInfo> questionInfoList = LoginCheck.getQuestionnaireData();
                List<List<QuestionInfo>> questionInfos= UtilOrp.averageAssign(questionInfoList,2);
                model.addAttribute("questionInfoOne",questionInfos.get(0));
                if(questionInfos.size() > 1) {
                    model.addAttribute("questionInfoTwo", questionInfos.get(1));
                }
                return "main_admin";
            default:
                model.addAttribute("msg","账号或密码错误，请重试！");
                return "index";
        }
    }
    //人员管理-admin
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public String person(Model model,HttpServletRequest request) {
        String role = request.getParameter("role");
        String param = request.getQueryString();
        List<UserDao> evaluationResList = LoginCheck.getUserInfoByRole(role);
        model.addAttribute("userInfoList",evaluationResList);
        return "person_manage";
    }
    //编辑
    @RequestMapping(value = "/personUpdate", method = RequestMethod.POST)
    public String personUpdate(Model model,HttpServletRequest request) {
        String userId = request.getParameter("inputId");
        String userName = request.getParameter("inputName");
        String role = request.getParameter("usertype");

        Boolean aBoolean = LoginCheck.personUpdate(userId, userName, role);
        if (aBoolean) {
            List<UserDao> evaluationResList = LoginCheck.getUserInfoByRole(role);
            model.addAttribute("userInfoList",evaluationResList);
            return "person_manage";
        } else {
            return "error";
        }
    }
    //添加
    @RequestMapping(value = "/personAdd", method = RequestMethod.POST)
    public String personAdd(Model model,HttpServletRequest request) throws UnsupportedEncodingException {
        String userId = request.getParameter("inputIdAdd");
        String userName = request.getParameter("inputNameAdd");
        userName = new String(userName.getBytes("iso8859-1"),"utf-8");
        String userWord = request.getParameter("inputWordAdd");

        String role = request.getParameter("usertypeAdd");

        Map userInfoMap = LoginCheck.getUserInfo(userId);
        Boolean aBoolean=true;
        if(userInfoMap.size() != 0){
            model.addAttribute("msg","用户id已存在");
        }else {
            aBoolean = LoginCheck.personAdd(userId, userName, role, userWord);
        }
        if (aBoolean) {
            List<UserDao> evaluationResList = LoginCheck.getUserInfoByRole(role);
            model.addAttribute("userInfoList",evaluationResList);
            return "person_manage";
        } else {
            return "error";
        }
    }
    //删除
    @RequestMapping(value = "/personDelete", method = RequestMethod.POST)
    public String personDelete(Model model,HttpServletRequest request) {
        String userId = request.getParameter("userId");
        String role = request.getParameter("role");
        String param = request.getQueryString();
        Boolean aBoolean = LoginCheck.personDelete(userId);
        if (aBoolean) {
            List<UserDao> evaluationResList = LoginCheck.getUserInfoByRole(role);
            model.addAttribute("userInfoList",evaluationResList);
            return "person_manage";
        } else {
            return "error";
        }
    }

}
