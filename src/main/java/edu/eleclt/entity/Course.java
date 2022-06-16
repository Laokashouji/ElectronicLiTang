package edu.eleclt.entity;

import edu.datastructure.MyDate;

import java.io.Serializable;
import java.util.Arrays;

public class Course implements Serializable {

    private static final long serialVersionUID = 1249794470754667710L;

    //上课时间、上课地点、课程资料、当前进度、已交作业、待交作业、课程群、考试时间和考试地点等信息。
    private static Integer totId = 0;
    private Integer id;
    private String name = "";
    private String teacher = "";
    private MyDate[] time = {};
    private String place = "";
    private String[] meterials = {};
    private int progress = 0;
    private String[] homeWorksFinished = {};
    private String[] homeWorksToDo = {};
    private String group = "";
    private MyDate examTime = new MyDate();
    private String examPlace = "";


    public Integer getId() {
        return id;
    }

    public static Integer getTotId() {
        return totId;
    }

    public static void setTotId(Integer totId) {
        Course.totId = totId;
    }

    public String getName() {
        return name;
    }

    public Course(String name, String teacher, MyDate[] time, String place, String[] meterials,
                  int progress, String[] homeWorksFinished, String[] homeWorksToDo, String group, MyDate examTime,
                  String examPlace) {
        this.id = ++totId;
        this.name = name;
        this.teacher = teacher;
        this.time = time;
        this.place = place;
        this.meterials = meterials;
        this.progress = progress;
        this.homeWorksFinished = homeWorksFinished;
        this.homeWorksToDo = homeWorksToDo;
        this.group = group;
        this.examTime = examTime;
        this.examPlace = examPlace;
    }

    public Course(String name, String teacher, MyDate[] time, String place, String group) {
        this.id = ++totId;
        this.name = name;
        this.teacher = teacher;
        this.time = time;
        this.place = place;
        this.group = group;
    }

    public Course() {
        totId++;
    }

    public Course(String name) {
        totId++;
        this.id = totId;
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public MyDate[] getTime() {
        return time;
    }

    public String getPlace() {
        return place;
    }

    public String[] getMeterials() {
        return meterials;
    }

    public void setExamTime(MyDate examTime) {
        this.examTime = examTime;
    }

    public void setExamPlace(String examPlace) {
        this.examPlace = examPlace;
    }

    public int getProgress() {
        return progress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTime(MyDate[] time) {
        this.time = time;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setMeterials(String[] meterials) {
        this.meterials = meterials;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setHomeWorksFinished(String[] homeWorksFinished) {
        this.homeWorksFinished = homeWorksFinished;
    }

    public void setHomeWorksToDo(String[] homeWorksToDo) {
        this.homeWorksToDo = homeWorksToDo;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String[] getHomeWorksFinished() {
        return homeWorksFinished;
    }

    public String[] getHomeWorksToDo() {
        return homeWorksToDo;
    }

    public String getGroup() {
        return group;
    }

    public MyDate getExamTime() {
        return examTime;
    }

    public String getExamPlace() {
        return examPlace;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time=" + Arrays.toString(time) +
                ", place=" + place +
                ", meterials=" + Arrays.toString(meterials) +
                ", progress=" + progress +
                ", homeWorksFinished=" + Arrays.toString(homeWorksFinished) +
                ", homeWorksToDo=" + Arrays.toString(homeWorksToDo) +
                ", group='" + group + '\'' +
                ", examTime=" + examTime.toString()+
                ", examPlace='" + examPlace + '\'' +
                '}';
    }
}
