package com.suma.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/29 0029
 * @Description 区域信息VO
 **/
@Data
public class AdvRegionVO {
    //上级区域信息,不填默认为0
    private Integer parentId;
    //区域名称
    @NotBlank
    @Length(max = 64)
    private String regionName;
    //显示顺序
    private Integer orderNum;
    
}
