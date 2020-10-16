package com.hwkj.search.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwkj.search.bean.ProKnowledge;
import com.hwkj.search.bean.QueryParam;
import com.hwkj.search.mapper.ProKnowledgeMapper;
import com.hwkj.search.service.IProKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/9/28 17:46
 */
@Service
public class ProKnowledgeServiceImpl extends ServiceImpl<ProKnowledgeMapper, ProKnowledge> implements IProKnowledgeService {

    @Autowired
    private ProKnowledgeMapper proKnowledgeMapper;

    @Override
    public List<ProKnowledge> getList(List<String> ids, QueryParam queryParam) {
        List<ProKnowledge> list =  proKnowledgeMapper.getList(ids,
                queryParam.getZzmc(),
                queryParam.getCslx(),
                queryParam.getQdlx(),
                queryParam.getSjdw(),
                queryParam.getYclx(),
                queryParam.getSortName(),
                queryParam.getSortTime(),
                queryParam.getSortType());
        return list;
    }
}
