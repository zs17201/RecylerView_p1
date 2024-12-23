package com.example.recyleview_p1.recycleviewclasses;

public class DataModel {

    private String name;
    private String detail;
    private int image; // Integer
    private int id;

    public DataModel(String name, String detail, int image, int id) {
        this.name = name;
        this.id = id;
        this.image = image;
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
