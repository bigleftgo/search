package com.hwkj.search.service;

import com.hwkj.search.bean.Knowledge;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 16:18
 */
public interface ILuceneService {

    void createIndex(Knowledge k);
}
