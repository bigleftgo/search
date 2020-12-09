package com.hwkj.search.service;

import com.hwkj.search.bean.*;
import com.hwkj.search.common.Result;
import com.hwkj.search.vo.HwkjSearchVo;
import com.hwkj.search.vo.SearchVo;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

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

    /**
     * 删除索引
     * @param id
     */
    void deleteIndex(String id) throws Exception;

    /**
     * 汉威科技查询索引
     * @param searchParam
     * @return
     */
    List<HwkjSearchVo> hwkjSearch(SearchParam searchParam) throws IOException, ParseException, InvalidTokenOffsetsException;

    /**
     * 汉威科技创建索引
     * @param k
     */
    void hwkjCreatIndex(List<Knowledge> k) throws IOException;
}
