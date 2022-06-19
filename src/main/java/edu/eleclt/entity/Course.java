package edu.eleclt.entity;

import edu.datastructure.MyArrayList;
import edu.datastructure.MyDate;

import java.io.Serializable;
import java.util.ArrayList;
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
    private MyArrayList<String> meterials = new MyArrayList<String>();
    private int progress = 0;
    private MyArrayList<String> homeWorksFinished = new MyArrayList<String>();
    private MyArrayList<String> homeWorksToDo = new MyArrayList<String>();
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

    public Course(String name, String teacher, MyDate[] time, String place, MyArrayList<String> meterials,
                  int progress, MyArrayList<String> homeWorksFinished, MyArrayList<String> homeWorksToDo, String group, MyDate examTime,
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
        id = ++totId;
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

    public MyArrayList<String> getMeterials() {
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

    public void setMeterials(MyArrayList<String> meterials) {
        this.meterials = meterials;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void setHomeWorksFinished(MyArrayList<String> homeWorksFinished) {
        this.homeWorksFinished = homeWorksFinished;
    }

    public void setHomeWorksToDo(MyArrayList<String> homeWorksToDo) {
        this.homeWorksToDo = homeWorksToDo;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public MyArrayList<String> getHomeWorksFinished() {
        return homeWorksFinished;
    }

    public MyArrayList<String> getHomeWorksToDo() {
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
                ", place='" + place + '\'' +
                ", meterials=" + meterials +
                ", progress=" + progress +
                ", homeWorksFinished=" + homeWorksFinished +
                ", homeWorksToDo=" + homeWorksToDo +
                ", group='" + group + '\'' +
                ", examTime=" + examTime +
                ", examPlace='" + examPlace + '\'' +
                '}';
    }

    public void addMeterial(String name) {
        this.meterials.add(name);
    }

    public void addHomework(String name) {
        this.homeWorksFinished.add(name);
        this.homeWorksToDo.delete(name);
    }
}
