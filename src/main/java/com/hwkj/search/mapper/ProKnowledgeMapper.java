package com.hwkj.search.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwkj.search.bean.ProKnowledge;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * com.hwkj.search.mapper
 *
 * @author Lenovo
 * @CreateTime 2020/9/28 17:41
 */
public interface ProKnowledgeMapper extends BaseMapper<ProKnowledge> {
    List<ProKnowledge> getList(@Param("ids") List<String> ids,
                               @Param("zzmc") String zzmc,
                               @Param("cslx") String cslx,
                               @Param("qdlx") String qdlx,
                               @Param("sjdw") String sjdw,
                               @Param("yclx") String yclx,
                               @Param("sortName") Integer sortName,
                               @Param("sortTime") Integer sortTime,
                               @Param("sortType") Integer sortType);
}
