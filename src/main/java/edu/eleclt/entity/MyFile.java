package edu.eleclt.entity;

import java.io.File;
import java.io.Serializable;

public class MyFile implements Serializable {
    private static final long serialVersionUID = 1249791234554667710L;

    private static Integer totId = 0;
    private Integer id;
    private long size = 0;
    private String name = "";
    private String md5 = "";
    private String type = null;
    private String url = null;
    private boolean isDelete = false;

    public MyFile(long size, String name, String type, String url, String md5) {
        this.id = ++totId;
        this.size = size;
        this.name = name;
        this.type = type;
        this.url = url;
        this.md5 = md5;
    }

    public static Integer getTotId() {
        return totId;
    }

    public static void setTotId(Integer totId) {
        MyFile.totId = totId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
