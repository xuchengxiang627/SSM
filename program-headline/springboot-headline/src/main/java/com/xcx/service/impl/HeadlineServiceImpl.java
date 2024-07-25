package com.xcx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xcx.mapper.HeadlineMapper;
import com.xcx.pojo.Headline;
import com.xcx.pojo.vo.PortalVo;
import com.xcx.service.HeadlineService;
import com.xcx.utils.JwtHelper;
import com.xcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author 27163
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-07-24 20:53:54
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline> implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> Ipage = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(Ipage, portalVo);

        List<Map> records = Ipage.getRecords();

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageNum", portalVo.getPageNum());
        pageInfo.put("pageSize", Ipage.getSize());
        pageInfo.put("totalPage", Ipage.getPages());
        pageInfo.put("totalSize", Ipage.getTotal());
        pageInfo.put("pageData", records);

        Map<String, Object> data = new HashMap<>();
        data.put("pageInfo", pageInfo);

        return Result.ok(data);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map<String, Object> detail = headlineMapper.selectHeadlineDetail(hid);
        Map<String, Object> data = new HashMap<>();
        data.put("headline", detail);

        // 修改阅读量
        Headline headline = new Headline();
        headline.setHid((Integer) detail.get("hid"));
        headline.setVersion((Integer) detail.get("version"));
        headline.setPageViews((Integer) detail.get("pageViews") + 1);

        headlineMapper.updateById(headline);

        return Result.ok(data);
    }

    @Override
    public Result publish(Headline headline, String token) {
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result updateHeadLine(Headline headline) {
        headline.setVersion(headlineMapper.selectById(headline.getHid()).getVersion());
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }

}




