package com.suma.dto;

import com.google.common.collect.Lists;
import com.suma.pojo.AdvDept;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 部门信息dto
 **/
@Data
public class AdvDeptDto extends AdvDept {
    //子集
    private List<AdvDeptDto> children = Lists.newArrayList();

    private String parentName;

    public static AdvDeptDto adapt(AdvDept advDept){
        AdvDeptDto advDeptDto = new AdvDeptDto();
        BeanUtils.copyProperties(advDept,advDeptDto);
        return advDeptDto;
    }

}
