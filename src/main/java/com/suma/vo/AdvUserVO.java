package com.suma.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description 用户管理vo
 **/
@Data
public class AdvUserVO {
    @NotNull
    private Integer deptId;
    @NotBlank
    @Length(max = 32)
    private String userName;
    @NotBlank
    @Length(max = 32)
    private String password;
    @NotBlank
    @Length(max = 11)
    private String phoneNumber;
    @NotEmpty
    private List<Integer> roleIds;

}
