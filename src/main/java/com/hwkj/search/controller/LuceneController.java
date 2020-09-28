package com.hwkj.search.controller;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
     * @param k 知识bean
     * @return
     */
    @PostMapping("/index")
    public RestResponse<String> creatIndex(@RequestBody List<Knowledge> k) {
        //根据文件path去服务器找文件信息
        try {
            luceneService.createIndex(k);
            return RestResponses.newSuccessResponse("索引创建成功", null);
        } catch (Exception e) {
            return RestResponses.newFailResponse(ErrorCode.INDEX_FAILURE,"文件被损坏"+e.getMessage());
        }
    }


    @PostMapping("/search")
    public RestResponse<List<SearchVo>> search(@RequestBody List<Search> search) {
        try {
            List<SearchVo> result = luceneService.search(search);
            return RestResponses.newSuccessResponse("查询完成", result);
        } catch (Exception e) {
            return RestResponses.newSuccessResponse("未查询到相关信息", null);
        }
    }
}
