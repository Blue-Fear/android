package com.example.eattime.Interface;

import com.example.eattime.Model.Restaurant;

import java.util.List;

public interface IBranchLoadListener {
    void onAllBranchLoadSuccess(List<Restaurant> restaurantList);
    void onAllBranchLoadFailed(String message);
}
