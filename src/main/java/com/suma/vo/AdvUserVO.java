package com.suma.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/22 0022
 * @Description
 **/
@Data
public class AdvUserVO {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;
    @NotBlank
    private String phoneNumber;
    @NotEmpty
    private List<Integer> roleIds;
    @NotNull
    private Integer deptId;

}
