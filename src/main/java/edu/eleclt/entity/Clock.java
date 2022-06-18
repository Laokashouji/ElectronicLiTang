package edu.eleclt.entity;

import edu.datastructure.MyDate;

public class Clock {

    private String name;
    private MyDate time;
    private String type;

    public Clock(String name, MyDate time, String type) {
        this.name = name;
        this.time = time;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
