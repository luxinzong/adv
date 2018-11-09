
package com.suma.service.impl;

import com.suma.dao.TsInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.TsInfo;
import com.suma.pojo.ServiceInfoExample;
import com.suma.pojo.TsInfo;
import com.suma.pojo.TsInfoExample;
import com.suma.service.BaseService;
import com.suma.service.ServiceInfoService;
import com.suma.service.TsService;
import com.suma.utils.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/18
 * @description:
 */
@Service
public class TsServiceImpl extends BaseServiceImpl<TsInfo, TsInfoExample, Long> implements TsService {
    @Resource
    public void setBaseDao(TsInfoMapper baseDao) {
        // TODO Auto-generated method stub
        super.setBaseDao(baseDao);
    }

    @Autowired
    private ServiceInfoService serviceInfoService;

    /**
     *
     *
     * @param tsInfo
     */
    @Override
    public void checkExist(TsInfo tsInfo) {
        TsInfoExample example = new TsInfoExample();

        if (tsInfo.getId() != null) {
            TsInfo oldInfo = findByPK(tsInfo.getId());
            if (tsInfo.getTsId().equals(oldInfo.getTsId())
                    && tsInfo.getTsName().equals(oldInfo.getTsName())) {
                return;
            } else if (tsInfo.getTsId().equals(oldInfo.getTsId())) {
                example.createCriteria().andTsNameEqualTo(tsInfo.getTsName());
                List<TsInfo> serviceInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(serviceInfos)) {
                    throw new BaseException("该TsName已存在");
                }
                return;
            } else if (tsInfo.getTsName().equals(oldInfo.getTsName())) {
                example.createCriteria().andTsIdEqualTo(tsInfo.getTsId());
                List<TsInfo> serviceInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(serviceInfos)) {
                    throw new BaseException("该TsId已存在");
                }
                return;
            }
        }

        example.or().andTsIdEqualTo(tsInfo.getTsId());
        List<TsInfo> tsInfos = selectByExample(example);
        if (tsInfos != null && tsInfos.size() > 0) {
            throw new BaseException("该tsId已占用");
        }
        example.clear();
        example.or().andTsNameEqualTo(tsInfo.getTsName());
        List<TsInfo> tsInfos1 = selectByExample(example);
        if (tsInfos1 != null && tsInfos1.size() > 0) {
            throw new BaseException("该tsName已占用");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteTsInfos(Long[] ids) {
        for (Long id : ids) {
            deleteByPK(id);
            ServiceInfoExample example = new ServiceInfoExample();
            example.createCriteria().andTidEqualTo(id);
            serviceInfoService.deleteByExample(example);
        }
    }
}
