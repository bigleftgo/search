package com.hwkj.search.controller;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.service.ILuceneService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * com.hwkj.search.controller
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 17:14
 */
@RestController
@Slf4j
@CrossOrigin
public class LuceneController {

    @Autowired
    private ILuceneService luceneService;

    /**
     * 创建索引
     * @param knowledge 知识bean
     * @return
     */
    @PostMapping("/index")
    public RestResponse<String> creatIndex(@RequestBody Knowledge knowledge){
        //根据文件path去服务器找文件信息
        luceneService.createIndex(knowledge);
        return RestResponses.newSuccessResponse("索引建立成功",null);
    }

    /**
     * 查询索引
     * @param info 查询信息
     * @return
     */
    @GetMapping("/search")
    public RestResponse<Map<String,Object>> search(@RequestParam(name = "info") List<String> info){
        luceneService.search(info);
        return RestResponses.newSuccessResponse("索引建立成功",null);
    }
}
