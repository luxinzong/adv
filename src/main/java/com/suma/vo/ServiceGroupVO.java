
package com.suma.vo;

import com.suma.utils.Insert;
import com.suma.utils.Update;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/22
 * @description:
 */
@Data
@ToString
public class ServiceGroupVO {
    @NotNull(groups = {Update.class})
    private Long sgid;

    @NotEmpty(groups = {Update.class, Insert.class})
    private String groupName;

    @NotNull(groups = {Update.class, Insert.class})
    private Integer type;

    private String comment;

    private Integer regionId;

    @NotNull(groups = {Update.class, Insert.class})
    private List<String> serviceNames;

}
