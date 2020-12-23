package com.hwkj.search.common;

import lombok.Data;

/**
 * com.hwkj.search.common
 *
 * @author Lenovo
 * @CreateTime 2020/12/16 16:57
 */
@Data
public class PostUrlContant {
    public static final String ADDRESS = "http://192.168.1.180:6618/DasService/DataService/mxalzskjava";
    /**
     * RM_INFORMATION_ITEM
     */
    public static final String RM_INFORMATION_ITEM_URL = ADDRESS + "/base/RM_INFORMATION_ITEM";
    /**
     * WELL_ACTIVITY
     */
    public static final String WELL_ACTIVITY_URL = ADDRESS + "/base/WELL_ACTIVITY";
    /**
     * RM_INFO_ITEM_CONTENT
     */
    public static final String RM_INFO_ITEM_CONTENT_URL = ADDRESS + "/base/RM_INFO_ITEM_CONTENT";
    /**
     * RM_KEYWORD
     */
    public static final String RM_KEYWORD_URL = ADDRESS + "/base/RM_KEYWORD";
    /**
     * WELL_STATUS
     */
    public static final String WELL_STATUS_URL = ADDRESS + "/base/WELL_STATUS";
    /**
     * WELL_ACTIVITY_COMPONENT
     */
    public static final String WELL_ACTIVITY_COMPONENT_URL = ADDRESS + "/base/WELL_ACTIVITY_COMPONENT";
    /**
     * WELL_MISC_DATA
     */
    public static final String WELL_MISC_DATA_URL = ADDRESS + "/base/WELL_MISC_DATA";
    /**
     * RM_INFO_ITEM_STATUS
     */
    public static final String RM_INFO_ITEM_STATUS_URL = ADDRESS + "/base/RM_INFO_ITEM_STATUS";
    /**
     * WELL_ACTIVITY_CAUSE
     */
    public static final String WELL_ACT_TECH_DES_URL = ADDRESS + "/base/WELL_ACT_TECH_DES";
    /**
     * WELL_ACTIVITY_CAUSE
     */
    public static final String WELL_ACTIVITY_CAUSE_URL = ADDRESS + "/base/WELL_ACTIVITY_CAUSE";
    /**
     * RM_DATA_CONTENT
     */
    public static final String RM_DATA_CONTENT_URL = ADDRESS + "/base/RM_DATA_CONTENT";
    /**
     * RM_FILE_CONTENT
     */
    public static final String RM_DOCUMENT_URL = ADDRESS + "/base/RM_DOCUMENT";
    /**
     * RM_FILE_CONTENT
     */
    public static final String RM_FILE_CONTENT_URL = ADDRESS + "/base/RM_FILE_CONTENT";
    /**
     * RM_INFO_ITEM_BA
     */
    public static final String RM_INFO_ITEM_BA_URL = ADDRESS + "/base/RM_INFO_ITEM_BA";
    /**
     * RM_CREATOR
     */
    public static final String RM_CREATOR_URL = ADDRESS + "/base/RM_CREATOR";
    /**
     * WELL_ACT_PRODUCE
     */
    public static final String WELL_ACT_PRODUCE_URL = ADDRESS + "/base/WELL_ACT_PRODUCE";

}
