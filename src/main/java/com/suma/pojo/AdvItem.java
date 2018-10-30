package com.suma.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/23
 * @description:
 */
@Data
public class AdvItem {

    private String advType;//广告位类型编码

    private String advSubType;//广告位子类型编码

    private Long assetType;//素材类型，图片（1）、和文本（2），视频（3）

    private String advURL;//广告素材所在路径（ 如果是IPQAM 播放的VOD 点播系统，视频素材该字段为空），文字时该值为空

    private String href;//素材超链接，点击广告图片的详情时使用。没有为空

    private String MD5;//素材MD5 验证码，如果值为0，不校验。对文件内容校验

    private String context;//文字滚动内容

    private Long duration;//非视频广告的显示持续时间，单位秒。如果是视频广告，则是视频广告实际时长。

    private Long displayTimes;//显示次数，默认显示一次，显示多次时，间隔时间由interval 参数控制

    private Long interval;//显示多次情况下的两次显示之间的间隔，单位秒，默认间隔为0

    private Long xPosition;//-1 为无效值

    private Long yPosition;

    private Long mHeight;

    private Long mWidth;

    private Long speed;//跑马灯速度，每秒多少像素

    private String fontSize;//字体大小

    private String fontColor;//字体颜色

    private String backgroudColor;//字幕背景颜色

    private Long direct;//方向1 右到左2 左到右3 上到下4 下到上

    private String channelIds;//多个以逗号隔开，请求EPG 广告时， 如果没有带请求参数channelId，返回时要把广告关联的频道带上

    private Map<String, String> extInfos;


}
