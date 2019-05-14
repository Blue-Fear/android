package com.example.eatstime;

public class Drink {
    int icon;
    String title;
    String detail;

    public Drink(int icon, String title, String detail) {
        this.icon = icon;
        this.title = title;
        this.detail = detail;
    }

    public Drink() {
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
