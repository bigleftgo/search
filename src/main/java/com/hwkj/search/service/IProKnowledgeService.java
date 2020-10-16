package com.hwkj.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwkj.search.bean.ProKnowledge;
import com.hwkj.search.bean.QueryParam;

import java.util.List;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/9/28 17:46
 */
public interface IProKnowledgeService extends IService<ProKnowledge> {


    List<ProKnowledge> getList(List<String> ids, QueryParam queryParam);
}
