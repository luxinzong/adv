package com.suma.constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/17 0017
 * @description
 */
public class AdvContants {

    public static Map<Integer, String> checkMap = new HashMap<>();//广告状态
    public static Map<Integer, String> typeMap = new HashMap<>();//点播直播

    public static Map<Integer,String > getCheckMap() {
        checkMap.put(STATUS_EDIT, STATUS_EDIT_DSC);
        checkMap.put(STATUS_PASS, STATUS_PASS_DSC);
        checkMap.put(STATUS_WAIT_CHECK, STATUS_WAIT_CHECK_DSC);
        checkMap.put(STATUS_NOT_PASS, STATUS_NOT_PASS_DSC);
        return checkMap;
    }

    public static Map<Integer, String> getTypeMap() {
        typeMap.put(ADV_SERVICE_TYPE_1, ADV_SERVICE_TYPE_1_DESC);
        typeMap.put(ADV_SERVICE_TYPE_2, ADV_SERVICE_TYPE_2_DESC);
        return typeMap;
    }

    /**
     * 广告素材常量
     */
    public static final Integer IMAGE_MATERIAL = 1;//图片广告

    public static final Integer VEDIO_MATERIAL = 3;//视频广告

    public static final Integer TEXT_MATERIAL = 2;//文字广告

    /**
     * 广告类型
     */
    public static final String START_ADV_TYPE = "001";//开机广告
    public static final String EPG_ADV_TYPE = "002";//EPG广告
    public static final String POP_ADV_TYPE = "003";//POP广告
    public static final String ONDEMOND_TIME_MOVE_BACK_LOOK_ADV_TYPE = "004";//点播、回看、时移广告
    public static final String VEDIO_ADV_type = "005";//视频广告


    /**
     * 广告状态常量
     */
    public static final Integer STATUS_EDIT = 1;//编辑状态
    public static final String STATUS_EDIT_DSC = "编辑状态";
    public static final Integer STATUS_WAIT_CHECK = 3;//待审核状态
    public static final String STATUS_WAIT_CHECK_DSC = "待审核状态";
    public static final Integer STATUS_PASS = 2;//审核通过
    public static final String STATUS_PASS_DSC = "审核通过";
    public static final Integer STATUS_NOT_PASS = 4;//审核不通过
    public static final String STATUS_NOT_PASS_DSC = "审核不通过";

    //频道分组信息
    public static final Integer ADV_SERVICE_CODE = 0;//所有频道
    public static final String ADV_SERVICE_DESCODE = "所有频道";
    public static final Integer ADV_SERVICE_GROUP_CODE = 1;//按频道分组
    public static final String ADV_SERVICE_DROUP_DESCODE = "按频道分组";


    //点播直播
    private static final Integer ADV_SERVICE_TYPE_1 = 0;//直播状态码
    private static final String ADV_SERVICE_TYPE_1_DESC = "直播";//直播状态码描述
    private static final Integer ADV_SERVICE_TYPE_2 = 1;//点播状态码
    private static final String ADV_SERVICE_TYPE_2_DESC = "点播";//点播状态码描述
    //频道类型
    public static final Integer SERVICE_TYPE_LIVE=0;

    public static final Integer SERVICE_TYPE_ON_DEMAND=1;

    public static final Integer SERVICE_TYPE_ON_DEMAND_ALL=2;

    //广告是否与频道广联
    public static final Long ADV_SERVICE_ASSOCIATE= 0L;//关联
    public static final Long ADV_SERVICE_NOT_ASSOCIATE= 1L;//直播不关联
    public static final Long ADV_CHANNEL_NOT_ASSOCIATE=2L;//点播不关联
}
