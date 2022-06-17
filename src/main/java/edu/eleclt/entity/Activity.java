package edu.eleclt.entity;

import edu.datastructure.MyDate;

import java.io.Serializable;

public class Activity implements Serializable {

    private static final long serialVersionUID = 1249745320754667710L;

    private static Integer totId = 0;
    private Integer id = 0;
    private String name = "";
    private String place = "";
    private MyDate time = new MyDate();
    private String type = "";
    private String tag = "";

    public Activity() {
        this.id = ++totId;
    }

    public Activity(String name, String place, MyDate time, String type, String tag) {
        this.id = ++totId;
        this.name = name;
        this.place = place;
        this.time = time;
        this.type = type;
        this.tag = tag;
    }

    public static Integer getTotId() {
        return totId;
    }

    public static void setTotId(Integer totId) {
        Activity.totId = totId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public MyDate getTime() {
        return time;
    }

    public void setTime(MyDate time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
