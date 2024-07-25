package com.xcx.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xcx.pojo.Headline;
import com.xcx.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author 27163
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-07-24 20:53:54
* @Entity com.xcx.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage<?> page, @Param("portalVo") PortalVo portalVo);

    Map<String, Object> selectHeadlineDetail(Integer hid);
}




