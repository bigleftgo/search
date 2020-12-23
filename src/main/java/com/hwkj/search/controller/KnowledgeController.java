package com.hwkj.search.controller;

import com.alibaba.fastjson.JSON;
import com.hwkj.search.bean.*;
import com.hwkj.search.common.*;
import com.hwkj.search.dto.Redundancy;
import com.hwkj.search.utils.CommonUtils;
import com.hwkj.search.utils.HttpUtil;
import com.hwkj.search.utils.UuidUtils;
import com.hwkj.search.vo.DocumentVo;
import com.hwkj.search.vo.KnowledgeVo;
import com.hwkj.search.vo.ProduceDataVo;
import com.hwkj.search.vo.WellStatusVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * com.hwkj.search.controller
 * 知识控制器
 *
 * @author Lenovo
 * @CreateTime 2020/12/16 9:48
 */
@RestController
@Slf4j
@CrossOrigin
public class KnowledgeController {

    @PostMapping("/saveCraftGeologyConstruction")
    public RestResponse<T> saveCraftGeologyConstruction(@RequestBody KnowledgeVo vo) {
        ArrayList<RmInformationItem> zsList = new ArrayList<>();
        ArrayList<RmInfoItemContent> contents = new ArrayList<>();
        ArrayList<RmKeyword> keywords = new ArrayList<>();
        ArrayList<WellStatus> statusesList = new ArrayList<>();
        ArrayList<WellActivityComponent> components = new ArrayList<>();
        ArrayList<WellMiscData> miscDatas = new ArrayList<>();
        ArrayList<RmInfoItemStatus> rmInfoItemStatuses = new ArrayList<>();
        ArrayList<WellActTechDes> wellActTechDes = new ArrayList<>();
        ArrayList<WellActivityCause> causes = new ArrayList<>();
        ArrayList<RmDataContent> dataContents = new ArrayList<>();
        ArrayList<RmDocument> documents = new ArrayList<>();
        ArrayList<RmFileContent> fileContents = new ArrayList<>();
        ArrayList<WellActivity> actList = new ArrayList<>();
        ArrayList<RmInfoItemBa> bas = new ArrayList<>();
        ArrayList<RmCreator> creators = new ArrayList<>();
        ArrayList<WellActProduce> produces = new ArrayList<>();
        //用于接收活动json
        String hdj;
        Redundancy no;//用于接收最大序号变化
        //知识参数
        RmInformationItem knowledgeParam = new RmInformationItem();
        BeanUtils.copyProperties(vo, knowledgeParam);
        knowledgeParam.setTitle(vo.getTitle_zs());//知识标题
        knowledgeParam.setInfo_item_subtype(CommonCode.INFO_ITEM_SUBTYPE.getDescription());//主键，表示知识
        knowledgeParam.setInformation_item_id(vo.getZs_id());
        knowledgeParam.setPpdm_guid(CommonCode.SOURCE.getDescription());
        zsList.add(knowledgeParam);

        //井活动
        WellActivity wellActivity = new WellActivity();
        BeanUtils.copyProperties(vo, wellActivity);
        wellActivity.setSource(CommonCode.SOURCE.getDescription());//主键
        //获取活动序号最大值
        String hdh1 = HttpUtil.httpGet(GetUrlContant.tyzdhdxh, null);
        no = CommonUtils.ConvertJsonDatasByOne(hdh1, Redundancy.class);
        if (vo.getAct_no() == null) {
            wellActivity.setActivity_obs_no(no.getMax_no());//主键，活动最大序号+1
        } else {
            //更新传参
            wellActivity.setActivity_obs_no(vo.getAct_no());
        }
        actList.add(wellActivity);
        //内部执行，因为要更新最大序号数
        hdj = HttpUtil.httpPost(JSON.toJSONString(actList), PostUrlContant.WELL_ACTIVITY_URL);

        //记录组件
        RmInfoItemContent content = new RmInfoItemContent();
        BeanUtils.copyProperties(vo, content);
        content.setInfo_item_subtype(CommonCode.INFO_ITEM_SUBTYPE.getDescription());//主键
        content.setInformation_item_id(vo.getZs_id());
        content.setContent_obs_no(1);//主键
        content.setWell_activity_source(CommonCode.SOURCE.getDescription());//来源
        if (vo.getAct_no() == null) {
            content.setActivity_obs_no(no.getMax_no());//最大活动序号+1，与well_activity表一致
        } else {
            //执行更新
            content.setActivity_obs_no(vo.getAct_no());
        }
        contents.add(content);

        //记录关键字
        RmKeyword keyword = new RmKeyword();
        BeanUtils.copyProperties(vo, keyword);
        keyword.setKeyword_id("1");//主键
        keyword.setInfo_item_subtype(CommonCode.INFO_ITEM_SUBTYPE.getDescription());
        keyword.setInformation_item_id(vo.getZs_id());
        keywords.add(keyword);

        //井状态
        List<WellStatusVo> statuses = vo.getStatuses();
        for (WellStatusVo status : statuses) {
            WellStatus wellStatus = new WellStatus();
            BeanUtils.copyProperties(status, wellStatus);
            wellStatus.setUwi(vo.getUwi());//主键
            wellStatus.setSource(CommonCode.SOURCE.getDescription());
            wellStatus.setStatus_date(vo.getStart_date());//施工日期
            statusesList.add(wellStatus);
        }

        //井活动组件
        WellActivityComponent activityComponent = new WellActivityComponent();
        BeanUtils.copyProperties(vo, activityComponent);
        activityComponent.setActivity_source(CommonCode.SOURCE.getDescription());
        activityComponent.setComponent_obs_no(1);//主键
        if (vo.getAct_no() == null) {
            activityComponent.setActivity_obs_no(no.getMax_no());//最大活动序号+1，与well_activity表一致
        } else {
            //执行更新
            activityComponent.setActivity_obs_no(vo.getAct_no());
        }
        components.add(activityComponent);

        //套管尺寸
        WellMiscData miscData = new WellMiscData();
        BeanUtils.copyProperties(vo, miscData);
        miscData.setSource(CommonCode.SOURCE.getDescription());//来源
        miscData.setMisc_data_type(CommonCode.CASING_SIZE.getDescription());//套管尺寸
        if (vo.getAct_no() == null) {
            miscData.setMisc_data_obs_no(no.getMax_no());//最大活动序号+1，与well_activity表一致
        } else {
            //执行更新
            miscData.setMisc_data_obs_no(vo.getAct_no());
        }
        miscData.setMisc_data_code("tgcc");//类型
        miscDatas.add(miscData);

        //记录-状态
        RmInfoItemStatus itemStatus = new RmInfoItemStatus();
        BeanUtils.copyProperties(vo, itemStatus);
        itemStatus.setInfo_item_subtype(CommonCode.INFO_ITEM_SUBTYPE.getDescription());
        itemStatus.setInformation_item_id(vo.getZs_id());//知识id
        itemStatus.setStatus_id("1");//主键
        rmInfoItemStatuses.add(itemStatus);

        //工艺参数
        WellActTechDes actTechDes = new WellActTechDes();
        BeanUtils.copyProperties(vo, actTechDes);
        actTechDes.setSource(CommonCode.SOURCE.getDescription());//主键，来源
        if (vo.getAct_no() == null) {
            actTechDes.setActivity_obs_no(no.getMax_no());//最大活动序号+1，与well_activity表一致
        } else {
            //执行更新
            actTechDes.setActivity_obs_no(vo.getAct_no());
        }
        wellActTechDes.add(actTechDes);

        //施工设计参数
        WellActivityCause wellActivityCause = new WellActivityCause();
        BeanUtils.copyProperties(vo, wellActivityCause);
        wellActivityCause.setSource(CommonCode.SOURCE.getDescription());
        if (vo.getAct_no() == null) {
            wellActivityCause.setActivity_obs_no(no.getMax_no());//最大活动序号+1，与well_activity表一致
        } else {
            //执行更新
            wellActivityCause.setActivity_obs_no(vo.getAct_no());
        }
        wellActivityCause.setCause_type(CommonCode.CAUSE_TYPE.getDescription());//上修原因
        causes.add(wellActivityCause);

        //文件信息保存
        for (int i = 0; i < vo.getDocs().size(); i++) {
            DocumentVo documentVo = vo.getDocs().get(i);
            String uuid = UuidUtils.uuid();
            //保存路径
            RmDataContent rmDataContent = new RmDataContent();
            BeanUtils.copyProperties(documentVo,rmDataContent);
            rmDataContent.setPhysical_item_id(uuid);//随机id，主键
            rmDataContent.setData_content_seq_no(1);
            rmDataContent.setSource(documentVo.getSuffix());//文档后缀（ppt，doc）
            dataContents.add(rmDataContent);

            RmDocument rmDocument = new RmDocument();
            BeanUtils.copyProperties(documentVo,rmDocument);
            documents.add(rmDocument);

            RmFileContent rmFileContent = new RmFileContent();
            BeanUtils.copyProperties(documentVo,rmFileContent);
            rmFileContent.setFile_id(UuidUtils.uuid());//主键随机id
            rmDataContent.setPhysical_item_id(uuid);
            fileContents.add(rmFileContent);

            //保存文档名和设计日期
            RmInformationItem item = new RmInformationItem();
            BeanUtils.copyProperties(documentVo,item);
            zsList.add(item);

            //保存设计单位
            RmInfoItemBa ba = new RmInfoItemBa();
            BeanUtils.copyProperties(documentVo,ba);
            ba.setContact_id("1");
            bas.add(ba);

            //保存设计人
            RmCreator rmCreator = new RmCreator();
            BeanUtils.copyProperties(documentVo,rmCreator);
            rmCreator.setCreator_id("1");
            creators.add(rmCreator);
        }

        //保存生产数据
        for (int i = 0; i < vo.getProduce().size(); i++) {
            ProduceDataVo dataVo = vo.getProduce().get(i);
            WellActProduce produce = new WellActProduce();
            BeanUtils.copyProperties(dataVo,produce);//拷贝取值时间和项目阶段
            produce.setUwi(vo.getUwi());
            produce.setSource(CommonCode.SOURCE.getDescription());//知识参数，主键
            produce.setActivity_obs_no(no.getMax_no());//插入活动最大序号
            produces.add(produce);

        }

        String zs = HttpUtil.httpPost(JSON.toJSONString(zsList), PostUrlContant.RM_INFORMATION_ITEM_URL);
        String zj = HttpUtil.httpPost(JSON.toJSONString(contents), PostUrlContant.RM_INFO_ITEM_CONTENT_URL);
        String kw = HttpUtil.httpPost(JSON.toJSONString(keywords), PostUrlContant.RM_KEYWORD_URL);
        String zt = HttpUtil.httpPost(JSON.toJSONString(statusesList), PostUrlContant.WELL_STATUS_URL);
        String actzj = HttpUtil.httpPost(JSON.toJSONString(components), PostUrlContant.WELL_ACTIVITY_COMPONENT_URL);
        String tg = HttpUtil.httpPost(JSON.toJSONString(miscDatas), PostUrlContant.WELL_MISC_DATA_URL);
        String jlzt = HttpUtil.httpPost(JSON.toJSONString(rmInfoItemStatuses), PostUrlContant.RM_INFO_ITEM_STATUS_URL);
        String gycs = HttpUtil.httpPost(JSON.toJSONString(wellActTechDes), PostUrlContant.WELL_ACT_TECH_DES_URL);
        String sjcs = HttpUtil.httpPost(JSON.toJSONString(causes), PostUrlContant.WELL_ACTIVITY_CAUSE_URL);
        String lj = HttpUtil.httpPost(JSON.toJSONString(dataContents), PostUrlContant.RM_DATA_CONTENT_URL);
        String doc = HttpUtil.httpPost(JSON.toJSONString(documents), PostUrlContant.RM_DOCUMENT_URL);
        String file = HttpUtil.httpPost(JSON.toJSONString(fileContents), PostUrlContant.RM_FILE_CONTENT_URL);
        String baj = HttpUtil.httpPost(JSON.toJSONString(bas), PostUrlContant.RM_INFO_ITEM_BA_URL);
        String creat = HttpUtil.httpPost(JSON.toJSONString(creators), PostUrlContant.RM_CREATOR_URL);
        String produce = HttpUtil.httpPost(JSON.toJSONString(produces), PostUrlContant.WELL_ACT_PRODUCE_URL);

        return RestResponses.judgeResult(zs,zj,kw,zt,actzj,tg,jlzt,gycs,sjcs,lj,doc,file,baj,creat,produce);
    }
}
