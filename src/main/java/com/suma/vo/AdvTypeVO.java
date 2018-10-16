package com.suma.vo;

import lombok.Data;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description: 广告类型父子类型联动显示
 */
@Data
public class AdvTypeVO {
    private String label;
    private String value;
    private Long id;
    private Set<AdvTypeVO> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        //if (!super.equals(o)) return false;
        AdvTypeVO advTypeVO = (AdvTypeVO) o;
        return Objects.equals(label, advTypeVO.label) &&
                Objects.equals(value, advTypeVO.value) &&
                Objects.equals(children, advTypeVO.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, value, children);
    }
}
