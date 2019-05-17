package com.example.eattime.Common;

import com.example.eattime.Model.Restaurant;
import com.example.eattime.Model.User;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_RESTAURANT_STORE = "RESTAURANT_SAVE";
    public static final String KEY_TABLE_LOAD_DONE = "TABLE_LOAD_DONE";
    public static String IS_LOGIN = "IsLogin";
    public static User currentUser;
    public static Restaurant currentRestaurant;
    public static int step = 0;  // Buoc dau tien = 0
    public static String city ="";
}
