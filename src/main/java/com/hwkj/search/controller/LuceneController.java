package com.hwkj.search.controller;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.common.Result;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
     *
     * @param knowledge 知识bean
     * @return
     */
    @PostMapping("/index")
    public RestResponse<String> creatIndex(@RequestBody Knowledge knowledge) {
        //根据文件path去服务器找文件信息
        luceneService.createIndex(knowledge);
        return RestResponses.newSuccessResponse("索引建立成功", null);
    }


    @PostMapping("/search")
    public RestResponse<Result<List<SearchVo>>> search(@RequestBody List<Search> search) {
        Result<List<SearchVo>> result = luceneService.search(search);
        return RestResponses.newSuccessResponse("查询完成", result);
    }
}
