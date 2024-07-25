package com.xcx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xcx.mapper.ScheduleMapper;
import com.xcx.service.ScheduleService;
import com.xcx.utils.PageBean;
import com.xcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xcx.pojo.Schedule;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public Result page(int pageSize, int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Schedule> schedules = scheduleMapper.queryAll();
        PageInfo<Schedule> pageInfo = new PageInfo<>(schedules);

        PageBean<Schedule> pageBean = new PageBean<>(currentPage, pageSize, pageInfo.getTotal(), pageInfo.getList());

        return Result.ok(pageBean);
    }

    @Override
    public Result delete(int id) {
        int i = scheduleMapper.delete(id);
        if (i > 0) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result save(Schedule schedule) {
        int rows = scheduleMapper.save(schedule);
        if (rows > 0) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }

    @Override
    public Result update(Schedule schedule) {
        if (schedule.getId() == null) return Result.fail("id为空");
        int rows = scheduleMapper.update(schedule);
        if (rows > 0) {
            return Result.ok(null);
        }
        return Result.fail(null);
    }
}
