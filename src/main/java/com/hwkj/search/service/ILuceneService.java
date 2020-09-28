package com.hwkj.search.service;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.common.Result;
import com.hwkj.search.vo.SearchVo;

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
     * @param search
     * @return
     * @throws Exception
     */
    List<SearchVo> search(List<Search> search) throws Exception;

}
