package com.example.eattime.Model;

public class Food {
    public int image;
    public String title;
    public String detail;

    public Food(int image, String title, String detail) {
        this.image = image;
        this.title = title;
        this.detail = detail;
    }

    public Food() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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
        return "Food{" +
                "image=" + image +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
