package com.suma.vo;

import com.suma.utils.Insert;
import com.suma.utils.Update;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@Data
public class NetVO {
    private Long id;

    @NotNull(groups = {Insert.class, Update.class})
    private String networkId;

    @NotNull(groups = {Insert.class, Update.class})
    private String networkName;

    private String regionName;
}
