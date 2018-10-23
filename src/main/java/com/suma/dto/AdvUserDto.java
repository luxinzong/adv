package com.suma.dto;

import com.suma.pojo.AdvDept;
import com.suma.pojo.AdvUser;
import lombok.Data;
import sun.dc.pr.PRError;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/
@Data
public class AdvUserDto extends AdvUser {

    private AdvDept advDept;

}
