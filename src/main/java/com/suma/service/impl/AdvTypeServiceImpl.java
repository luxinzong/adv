package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvTypeMapper;
import com.suma.exception.AdvTypeException;
import com.suma.pojo.AdvItem;
import com.suma.pojo.AdvType;
import com.suma.pojo.AdvTypeExample;
import com.suma.service.AdvTypeService;
import com.suma.service.BaseService;
import com.suma.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/15
 * @description:
 */
@Service
public class AdvTypeServiceImpl extends BaseServiceImpl<AdvType, AdvTypeExample, Long> implements AdvTypeService {
    @Resource
    public void setBaseDao(AdvTypeMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    /**
     * 根据广告类型和广告子类型查询广告类型ID
     * @param advType
     * @param advSubType
     * @return
     */
    @Override
    public Long getAdvTypeIdByAdvTypeAndSubType(String advType, String advSubType) {
        if (StringUtils.isAnyBlank(advSubType, advType)) {
            throw new AdvTypeException(ExceptionConstants.ADV_TYPE_PARAMS_MISSING);
        }
        AdvTypeExample example = new AdvTypeExample();
        example.createCriteria().andAdvtypeEqualTo(advType).andAdvsubtypeEqualTo(advSubType);
        List<AdvType> advTypes = selectByExample(example);
        if (!CollectionUtils.isEmpty(advTypes)) {
            return advTypes.get(0).getId();
        }
        return null;
    }

    @Override
    public AdvItem setAdvItem(Long advTypeId) {
        AdvType advType = findByPK(advTypeId);
        AdvItem advItem = new AdvItem();
        advItem.setAdvType(advType.getAdvtype());
        advItem.setAdvSubType(advType.getAdvsubtype());
        return advItem;
    }
}
