package com.example.eattime.Interface;

import java.util.List;

public interface IAllRestaurantLoadListener {
    void onAllRestaurantLoadSuccess(List<String> areaNameList);
    void onAllRestaurantLoadFailed(String message);
}
