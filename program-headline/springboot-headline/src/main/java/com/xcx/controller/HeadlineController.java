package com.xcx.controller;

import com.xcx.pojo.Headline;
import com.xcx.service.HeadlineService;
import com.xcx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private HeadlineService headlineService;

    /**
     * 发布头条
     */
    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader("token") String token) {
        return headlineService.publish(headline, token);
    }

    /**
     * 修改头条回显
     */
    @PostMapping("findHeadlineByHid")
    public Result findHeadLineByHid(Integer hid) {
        Headline headline = headlineService.getById(hid);
        Map<String, Object> headlineMap = new HashMap<>() {{
            put("hid", hid);
            put("title", headline.getTitle());
            put("article", headline.getArticle());
            put("type", headline.getType());
        }};
        return Result.ok(new HashMap<String, Object>() {{
            put("headline", headlineMap);
        }});
    }

    /**
     * 修改头条
     */
    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        return headlineService.updateHeadLine(headline);
    }

    /**
     * 删除头条
     */
    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid) {
        headlineService.removeById(hid);
        return Result.ok(null);
    }
}
