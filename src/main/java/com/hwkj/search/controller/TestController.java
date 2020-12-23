package com.hwkj.search.controller;

import com.hwkj.search.bean.RmInfoItemContent;
import com.hwkj.search.bean.RmInformationItem;
import com.hwkj.search.bean.WellActivity;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.Result;
import com.hwkj.search.service.RmInfoItemContentService;
import com.hwkj.search.service.RmInformationItemService;
import com.hwkj.search.service.WellActivityService;
import com.hwkj.search.utils.CommonUtils;
import com.hwkj.search.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * com.hwkj.search.controller
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 9:50
 */
@RestController
@Slf4j
@CrossOrigin
public class TestController {
    @Autowired
    private WellActivityService wellActivityService;
    @Autowired
    private RmInformationItemService informationItemService;
    @Autowired
    private RmInfoItemContentService infoItemContentService;

    @PostMapping("save")
    public RestResponse<?> sava(@RequestBody TestVo vo) {

        List<WellActivity> activities = new ArrayList<>();
        List<RmInfoItemContent> contents = new ArrayList<>();
        List<RmInformationItem> items = new ArrayList<>();
        WellActivity activity = new WellActivity();
        RmInfoItemContent content = new RmInfoItemContent();
        RmInformationItem item = new RmInformationItem();
        BeanUtils.copyProperties(vo, activity);
        BeanUtils.copyProperties(vo, content);
        BeanUtils.copyProperties(vo, item);
        activities.add(activity);
        contents.add(content);
        items.add(item);


        Result<String> result1 = wellActivityService.save(activities);
        Result<String> result2 = informationItemService.save(items);
        Result<String> result3 = infoItemContentService.save(contents);
        log.info("打印保存信息----->{},{},{}",result1.getData(),result2.getData(),result3.getData());
        return CommonUtils.ConvertJsonJudgmentIsSuccess(result1.getData(),result2.getData(),result3.getData());
    }
}
