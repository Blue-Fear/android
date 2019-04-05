package com.example.new7wonders;

public class Wonder {
    public int image;
    public String wonder;
    public String date;
    public int flag;

    public Wonder(int image, String wonder, String date, int flag) {
        this.image = image;
        this.wonder = wonder;
        this.date = date;
        this.flag = flag;
    }

    public Wonder() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getWonder() {
        return wonder;
    }

    public void setWonder(String wonder) {
        this.wonder = wonder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Wonder{" +
                "image=" + image +
                ", wonder='" + wonder + '\'' +
                ", date='" + date + '\'' +
                ", flag=" + flag +
                '}';
    }
}
