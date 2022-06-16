package edu.datastructure;

import java.io.Serializable;
import java.util.List;

public class MyDate implements Serializable{

    private static final long serialVersionUID = 123445123122892145L;

    private String[] rowTime = new String[2];
    private String weekday = null;
    private int Y = 0;
    private int M = 0;
    private int D = 0;
    private int H = 0;
    private int m = 0;
    private int s = 0;
    private String startTime = "";
    private String endTime = "";
    private String formatTime ="";

    public String getFormatTime() {
        return formatTime;
    }

    public void setFormatTime(String formatTime) {
        this.formatTime = formatTime;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public int getY() {
        return Y;
    }

    public void setY(int y) {
        Y = y;
    }

    public int getM() {
        return M;
    }

    public void setM(int m) {
        M = m;
    }

    public int getS() {
        return s;
    }

    public void setS(int s) {
        this.s = s;
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

    public int getD() {
        return D;
    }

    public void setD(int d) {
        D = d;
    }

    public int getH() {
        return H;
    }

    public void setH(int h) {
        H = h;
    }

    public String[] getRowTime() {
        return rowTime;
    }

    public void setRowTime(String[] rowTime) {
        this.rowTime = rowTime;
    }

    public MyDate(){
    }

    public MyDate(String weekday, String startTime, String endTime) {
        this.weekday = weekday;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public MyDate(String weekday, List<String> time) {
        this.weekday = weekday;
        this.rowTime[0] = time.get(0);
        this.rowTime[1] = time.get(1);
        startTime = time.get(0).substring(time.get(0).indexOf("T") + 1, time.get(0).lastIndexOf(":"));
        int l = (Integer.parseInt(startTime.split(":")[0]) + 8) % 24;
        this.startTime = l + startTime.substring(startTime.indexOf(":"));
        endTime = time.get(1).substring(time.get(1).indexOf("T") + 1, time.get(1).lastIndexOf(":"));
        l = (Integer.parseInt(endTime.split(":")[0]) + 8) % 24;
        this.endTime = l + endTime.substring(endTime.indexOf(":"));
    }
}
