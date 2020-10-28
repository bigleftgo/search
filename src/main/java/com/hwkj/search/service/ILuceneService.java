package com.hwkj.search.service;

import com.hwkj.search.bean.*;
import com.hwkj.search.common.Result;
import com.hwkj.search.vo.SearchVo;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 16:18
 */
public interface ILuceneService {

    /**
     * 创建索引
     * @param k
     */
    void createIndex(List<Knowledge> k) throws Exception;
    /**
     * 查询索引
     * @param searchParam
     * @return
     * @throws Exception
     */
    List<SearchVo> search(SearchParam searchParam) throws Exception;

    /**
     * 更新索引
     * @param k
     * @return
     */
    void updateIndex(ProUpKonwledge k) throws IOException;
}
