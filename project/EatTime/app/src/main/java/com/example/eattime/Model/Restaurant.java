package com.example.eattime.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    //resId de chon tat ca tables cua restaurant do
    // set resId thu cong khi user load restaurant
    private String name, address, resId;

    public Restaurant() {
    }

    public Restaurant(String name, String address, String resId) {
        this.name = name;
        this.address = address;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    protected Restaurant(Parcel in) {
        name = in.readString();
        address = in.readString();
        resId = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(resId);
    }
}
