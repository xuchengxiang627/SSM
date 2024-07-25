package com.xcx.service;

import com.xcx.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xcx.pojo.vo.PortalVo;
import com.xcx.utils.Result;

/**
* @author 27163
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-07-24 20:53:54
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline, String token);

    Result updateHeadLine(Headline headline);
}
