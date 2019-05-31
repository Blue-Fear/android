package com.example.eattime.Common;

import com.example.eattime.Model.Restaurant;
import com.example.eattime.Model.Table;
import com.example.eattime.Model.User;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_RESTAURANT_STORE = "RESTAURANT_SAVE";
    public static final String KEY_TABLE_LOAD_DONE = "TABLE_LOAD_DONE";
    public static final String KEY_TIME_SLOT = "TIME_SLOT" ;
    public static final String KEY_STEP = "STEP";
    public static final String KEY_TABLE_SELECTED = "TABLE_SELECTED";
    public static final int TIME_SLOT_TOTAL = 6;
    public static String IS_LOGIN = "IsLogin";
    public static User currentUser;
    public static Restaurant currentRestaurant;
    public static int step = 0;  // Buoc dau tien = 0
    public static String city ="";
    public static Table currentTable;

    public static String convertTimeSlotToString(int slot) {
        switch (slot)
        {
            case 0:
                return "9:00-11:00";
            case 1:
                return "11:00-13:00";
            case 2:
                return "13:00-15:00";
            case 3:
                return "15:00-17:00";
            case 4:
                return "17:00-19:00";
            case 5:
                return "19:00-21:00";
            default:
                return "Closed";
        }
    }
}
