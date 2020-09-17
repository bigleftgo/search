package com.hwkj.search.service;

import com.hwkj.search.bean.Knowledge;

import java.util.List;

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
    void createIndex(Knowledge k);

    /**
     * 查询索引信息
     * @param info
     */
    void search(List<String> info);

}
