package com.suma.service;

import com.suma.pojo.MaterialType;
import com.suma.pojo.MaterialTypeExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/16
 * @description:
 */
public interface MaterialTypeService extends BaseService<MaterialType, MaterialTypeExample, Long> {
    Long[] getMeterialByTypeId(Long advTypeId);

    void deleteByMaterialId(Long id);

    String getTypeIdsByMaterialId(Long id);
}
