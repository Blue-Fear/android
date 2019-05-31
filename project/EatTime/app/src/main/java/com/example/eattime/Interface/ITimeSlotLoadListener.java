package com.example.eattime.Interface;

import com.example.eattime.Model.TimeSlot;

import java.sql.Time;
import java.util.List;

public interface ITimeSlotLoadListener {
    void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList);
    void onTimeSlotLoadFailed(String message);
    void onTimeSlotLoadEmpty();
}
