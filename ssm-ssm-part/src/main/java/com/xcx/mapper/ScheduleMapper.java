package com.xcx.mapper;

import com.xcx.pojo.Schedule;

import java.util.List;

public interface ScheduleMapper {
    List<Schedule> queryAll();

    int delete(int id);

    int save(Schedule schedule);

    int update(Schedule schedule);
}
