package com.suma.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MovieMaterial {
    private Long id;

    /**
     * 视频源ID
     */
    private Long movieId;

    /**
     * 视频名称
     */
    private String movieName;

    /**
     * 视频源地址
     */
    private String movieUrl;

    /**
     * 视频提供商ID
     */
    private Long movieProviderId;

    /**
     * 视频源下载地址
     */
    private String downLoadUrl;

    /**
     * 视频时长
     */
    private String timeLength;

    /**
     * 上映时间
     */
    private String time;

    /**
     * 上架时间
     */
    private String updateTime;

    /** -----------------------剧集资源----------------------- */

    /**
     * 剧集名称
     */
    private String titleName;

    /**
     * 剧集海报地址下载
     */
    private String posterUrl;

    /**
     * 集数标识
     */
    private String episodeId;

    /***
     * 集名称
     */
    private String episodeName;

}