package com.suma.dto;

import com.suma.pojo.AdvDept;
import com.suma.pojo.AdvUser;
import lombok.Data;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description AdvUserDto包含对应部门对象
 **/
@Data
public class AdvUserDto extends AdvUser {

    private AdvDept advDept;

}
