package com.suma.service;

import com.suma.pojo.AdvItem;
import com.suma.pojo.AdvType;
import com.suma.pojo.AdvTypeExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
public interface AdvTypeService extends BaseService<AdvType, AdvTypeExample, Long> {
    Long getAdvTypeIdByAdvTypeAndSubType(String advType,String advSubType);

    AdvItem setAdvItem(Long advTypeId);
}
