package com.xcx.service;

import com.xcx.pojo.Schedule;
import com.xcx.utils.Result;

public interface ScheduleService {
    Result page(int pageSize, int currentPage);

    Result delete(int id);

    Result save(Schedule schedule);

    Result update(Schedule schedule);
}
