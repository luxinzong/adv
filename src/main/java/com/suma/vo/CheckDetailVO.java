package com.suma.vo;

import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvLocation;
import com.suma.pojo.AdvMaterial;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/11/08
 * @description:
 */

@Data
public class CheckDetailVO {

    private Long advId;//广告ID

    private String advName;//广告名称

    private String advtypename;//广告类型名称

    private String advsubtypename;//广告子类型名称

    private String startDate;

    private String endDate;

    private String periodTimeStart;

    private String periodTimeEnd;

    private Integer status;//广告状态

    private Date createdTime;//创建时间

    private Date lastEditTime;//最后修改时间

    private String checkUser;//审核人

    private String checkNote;//审核意见

    private Date checkTime;//审核时间

    private List<String> regionNames;//区域码

    private Integer serviceStatus;//频道分组状态标记，-1:不按频道分组,0：表示按频道进行分组，1：在所有频道上有效，直播，2:在所有频道上有效，点播

    private Integer serviceType;//频道类型 0直播 1 点播

    private List<String> serviceGroupNames;//多个以逗号隔开，请求EPG 广告时， 如果没有带请求参数channelId，返回时要把广告关联的频道带上

    //广告位
    private AdvLocation location;

    private Integer materialType;//素材类型，图片（1）、和文本（2），视频（3）

    //图片or视频
    private List<CheckMaterialVO> materials;

    //文字
    private List<AdvFlyWord> flyWords;

    //CA
    private String CA;


}
