package com.suma.vo;

import com.suma.pojo.AdvItem;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @author: luxinzong
 * @date: 2018/11/21 0021
 * @description
 */
@Data
public class AdvGathererVO {

    /**
     * 返回结果码
     */
    private String resultCode;

    /**
     * 返回结果描述
     */
    private String resultDesc;

    /**
     * 返回广告数
     */
    private Long resultCount;

    /**
     * sessionId
     */
    private String sessionId;

    /**
     * 检查时间间隔
     */
    private Long checkInterval;

    /**
     * 添加版本识别号
     */
    private Integer version;

    /**
     * 导入器名称
     */
    private String name;

    /**
     * 导入器所属区域
     */
    private Integer region;

    /**
     * 导入器状态
     */
    private Integer status;

    /**
     * 导入器上一个状态
     */
    private Integer lastStatus;

    /**
     * 广告收集器
     *
     * String 标识广告类型 开机/字幕。。。。等9种广告类型
     *
     * AdvItem 某类型广告对应的每一个资源对象
     *
     * MultipartFile 广告资源对应的资源文件
     */
    private Map<String, Map<AdvItem,MultipartFile>> map;


}
