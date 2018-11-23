package com.suma.constants;

import org.apache.commons.collections.MultiMap;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.omg.CORBA.StringHolder;

import java.util.HashMap;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/17 0017
 * @description
 */
public class AdvContants {

    /**
     * 广告素材常量
     */
    public static final Integer IMAGE_MATERIAL = 1;//图片广告

    public static final Integer VEDIO_MATERIAL = 3;//视频广告

    public static final Integer TEXT_MATERIAL = 2;//文字广告

    /**
     * 广告类型
     */
    public static final String START_LOGO_ADV_SUBTYPE = "开机LOGO广告";//
    public static final Long START_LOGO_ADV_SUBTYPE_ID = 1L;
    public static final String START_MACHINE_ADV_SUBTYPE = "开机广告";//
    public static final Long START_MACHINE_ADV_SUBTYPE_ID = 2L;
    public static final String CUT_CHANNEL_ADV_SUBTYPE = "切台PF广告";//
    public static final Long CUT_CHANNEL_ADV_SUBTYPE_ID = 3L;//
    public static final String VOLUM_ADV_SUBTYPE = "音量条广告";//
    public static final Long VOLUM_ADV_SUBTYPE_ID = 4L;
    public static final String MAIN_MENU_ADV_SUBTYPE = "主菜单广告";//
    public static final Long MAIN_MENU_ADV_SUBTYPE_ID = 5L;
    public static final String LIST_ADV_SUBTYPE = "节目列表广告";//
    public static final Long LIST_ADV_SUBTYPE_ID = 6L;//
    public static final String POP_ADV_SUBTYPE = "弹出广告";//
    public static final Long POP_ADV_SUBTYPE_ID = 7L;//
    public static final String BEFORE_MOVIE_ADV_SUBTYPE = "片头广告";//
    public static final Long BEFORE_MOVIE_ADV_SUBTYPE_ID = 8L;//
    public static final String SUSPAND_MOVIE_ADV_SUBTYPE = "暂停广告";//
    public static final Long SUSPAND_MOVIE_ADV_SUBTYPE_ID = 9L;//

    /**
     * 广告位
     */
    public static final Long START_LOGO_ADV_LOCATION_ID = 1L;
    public static final Long START_ADV_LOCATION_ID = 2L;

    /**
     * 广告状态常量
     */
    public static final Integer STATUS_EDIT = 1;//编辑状态
    public static final String STATUS_EDIT_DSC = "编辑状态";
    public static final Integer STATUS_WAIT_CHECK = 2;//待审核状态
    public static final String STATUS_WAIT_CHECK_DSC = "待审核状态";
    public static final Integer STATUS_PASS = 3;//审核通过
    public static final String STATUS_PASS_DSC = "审核通过";
    public static final Integer STATUS_NOT_PASS = 4;//审核不通过
    public static final String STATUS_NOT_PASS_DSC = "审核不通过";
    public static final String STATUS_PUTTING_DSC = "播发状态";
    public static final Integer STATUS_PUTTING = 5;//播发状态
    public static final String STATUS_STOP_DSC = "停播状态";
    public static final Integer STATUS_STOP = 6;//停播
    public static final String STATUS_FAIL_DESC = "播发失败";
    public static final Integer STATUS_FAIL = 7;//播发失败


    //点播直播
    public static final Integer ADV_SERVICE_TYPE_1 = 0;//直播状态码
    public static final String ADV_SERVICE_TYPE_1_DESC = "直播";//直播状态码描述
    public static final Integer ADV_SERVICE_TYPE_2 = 1;//点播状态码
    public static final String ADV_SERVICE_TYPE_2_DESC = "点播";//点播状态码描述
    public static final Integer ADV_SERVICE_TYPE_3 = 2;//所有节目
    public static final String ADV_SERVICE_TYPE_3_DESC = "所有节目";

    //频道分组状态标记
    public static final Long SERVICE_GROUP_STATUS_ACTIVE = 0L;//按频道分组标记
    public static final Long SERVICE_GROUP_STATUS_SLEEP = -1L;//不按频道进行分组
    public static final Long SERVICE_GROUP_STATUS_LIVE = 1L;//不按频道进行分组，所有频道，直播
    public static final Long SERVICE_GROUP_STATUS_VOD = 2L;//不按频道进行分组，所有频道，点播

    //频道类型
    public static final Integer SERVICE_TYPE_LIVE = 0;

    public static final Integer SERVICE_TYPE_ON_DEMAND = 1;

    public static final Integer SERVICE_TYPE_ON_DEMAND_ALL = 2;


    //广告是否与频道广联
    public static final Long ADV_SERVICE_ASSOCIATE = 0L;//关联
    public static final Long ADV_SERVICE_NOT_ASSOCIATE = 1L;//直播不关联
    public static final Long ADV_CHANNEL_NOT_ASSOCIATE = 2L;//点播不关联
    public static final Long ADV_NOT_ASSOCIATE = -1L;//全部不关联

    //广告请求常量
    public static final String LOCATION_IS_NULL = "未填写广告位信息";
    public static final String REGION_ID_IS_NULL = "未填写区域信息";
    public static final String MATERIAL_IS_NULL = "未填写资源信息";
    public static final String FLYWORDS_IS_NULL = "未填写字幕信息";
    public static final String SERVICE_GROUP_IS_NULL = "未填写频道信息";
}
