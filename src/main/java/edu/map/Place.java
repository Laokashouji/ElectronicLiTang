package edu.map;

import java.io.Serializable;

public class Place implements Serializable {

//    private static final long serialVersionUID = 1234416521122892189L;

    private String name = "";
    private int school = 0;
    private boolean isTarget = false;
    private String[] courses = {};
    private String[] time = {};

    public Place(String name, int school) {
        this.name = name;
        this.school = school;
    }

    public Place(String name, int school, boolean isTarget) {
        this.name = name;
        this.school = school;
        this.isTarget = isTarget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public boolean isTarget() {
        return isTarget;
    }

    public void setTarget(boolean target) {
        isTarget = target;
    }

    public String[] getCourses() {
        return courses;
    }

    public void setCourses(String[] courses) {
        this.courses = courses;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }
}
