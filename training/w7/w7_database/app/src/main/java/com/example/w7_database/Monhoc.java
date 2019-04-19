package com.example.w7_database;

public class Monhoc {
    //thuc tinh cua doi tuong mID
    private int mID;
    private String mTen;
    private String mMa;
    private String mTiet;

    public Monhoc(String mTen, String mMa, String mTiet) {
        this.mTen = mTen;
        this.mMa = mMa;
        this.mTiet = mTiet;
    }

    public Monhoc(int mID, String mTen, String mMa, String mTiet) {
        this.mID = mID;
        this.mTen = mTen;
        this.mMa = mMa;
        this.mTiet = mTiet;
    }

    public Monhoc() {

    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public String getmMa() {
        return mMa;
    }

    public void setmMa(String mMa) {
        this.mMa = mMa;
    }

    public String getmTiet() {
        return mTiet;
    }

    public void setmTiet(String mTiet) {
        this.mTiet = mTiet;
    }

    @Override
    public String toString() {
        return "Monhoc{" +
                "mID=" + mID +
                ", mTen='" + mTen + '\'' +
                ", mMa='" + mMa + '\'' +
                ", mTiet='" + mTiet + '\'' +
                '}';
    }
}
