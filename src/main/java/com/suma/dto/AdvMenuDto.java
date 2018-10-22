package com.suma.dto;

import com.google.common.collect.Lists;
import com.suma.pojo.AdvMenu;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class AdvMenuDto extends AdvMenu {

    //属性结构
    private List<AdvMenuDto> children = Lists.newArrayList();
    //父类名称
    private String parentName;

    public static AdvMenuDto adapt(AdvMenu advMenu){
        AdvMenuDto advMenuDto = new AdvMenuDto();
        BeanUtils.copyProperties(advMenu,advMenuDto);

        return advMenuDto;
    }


}
