package com.suma.service.impl;

import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvInfoMapper;
import com.suma.dao.AdvMaterialMapper;
import com.suma.exception.AdvInfoException;
import com.suma.pojo.AdvInfo;
import com.suma.pojo.AdvInfoExample;
import com.suma.pojo.AdvMaterial;
import com.suma.pojo.AdvMaterialExample;
import com.suma.service.AdvInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @auther: luxinzong
 * @date: 2018/10/15
 */
@Service
public class AdvInfoServiceImpl extends BaseServiceImpl<AdvInfo, AdvInfoExample, Long> implements AdvInfoService {

    @Autowired
    AdvInfoMapper advInfoMapper;
    @Autowired
    AdvMaterialMapper advMaterialMapper;

    @Resource
    public void setBaseDao(AdvInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Override
    public List<AdvInfo> selectAdvInfo(Map<String,Object> map) {
        return advInfoMapper.selectAdvInfo(map);
    }

    @Override
    public AdvInfo findById(Long id) {
        return advInfoMapper.findById(id);
    }

    /**
     * 获取视频源
     * @return
     */
    @Override
    public List<AdvMaterial> getVedioMaterial(){
        AdvMaterialExample example = new AdvMaterialExample();
        example.createCriteria().andMaterialTypeEqualTo(3);
        return advMaterialMapper.selectByExample(example);
    }

    /**
     * 删除广告信息
     * @param list
     */
    @Override
    public void deleteByAdvInfoIds(List<Long> list) {
        if (!CollectionUtils.isEmpty(list)) {
            AdvInfoExample example = new AdvInfoExample();
            example.createCriteria().andIdIn(list);
            advInfoMapper.deleteByExample(example);
        }
    }

    /**
     * 判断广告是否存在
     * @param advInfo
     */
    @Override
    public void judgeAdvInfo(AdvInfo advInfo) {
        String name = advInfo.getName();
        //检查该名称广告是否存在，如果存在提示用户该广告名称已经存在
        AdvInfoExample example = new AdvInfoExample();
        example.createCriteria().andNameEqualTo(name);
        List<AdvInfo> advInfoList = advInfoMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(advInfoList)) {
            throw new AdvInfoException(ExceptionConstants.INFO_EXCEPTION_INFO_NAME_IS_EXIT);
        }
    }

    /**
     * 根据光阿狗ID 和广告类型 把广告信息查询出来
     * @param advInfoIds
     * @param advTypeId
     * @return
     */
    @Override
    public List<AdvInfo> getAdvInfoByRegionIdAndAdvTypeId(List<Long> advInfoIds, Long advTypeId) {
        AdvInfoExample example = new AdvInfoExample();
        if (!CollectionUtils.isEmpty(advInfoIds)) {
            example.createCriteria().andIdIn(advInfoIds).andAdvTypeIdEqualTo(advTypeId);
        }
        return selectByExample(example);
    }
}
