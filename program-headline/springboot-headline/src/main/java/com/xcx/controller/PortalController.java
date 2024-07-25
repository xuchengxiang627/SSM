package com.xcx.controller;

import com.xcx.pojo.vo.PortalVo;
import com.xcx.service.HeadlineService;
import com.xcx.service.TypeService;
import com.xcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 首页控制器
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {

    @Autowired
    private TypeService typeService;
    @Autowired
    private HeadlineService headlineService;

    /**
     * 查询所有分类
     */
    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        return typeService.findAllTypes();
    }

    /**
     * 查询新闻分页
     */
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo) {
        return headlineService.findNewsPage(portalVo);
    }

    /**
     * 查询新闻详情
     */
    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid) {
        return headlineService.showHeadlineDetail(hid);
    }
}
