package com.suma.dto;

import com.google.common.collect.Lists;
import com.suma.pojo.AdvRegion;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/29 0029
 * @Description
 **/
@Data
public class AdvRegionDto extends AdvRegion {

    //子集
    private List<AdvRegionDto> children = Lists.newArrayList();

    private String parentName;

    public static AdvRegionDto adapt(AdvRegion advRegion){
        AdvRegionDto advDeptDto = new AdvRegionDto();
        BeanUtils.copyProperties(advRegion,advDeptDto);
        return advDeptDto;
    }


}
