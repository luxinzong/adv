
package com.suma.service.impl;

import com.suma.dao.TsInfoMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.TsInfo;
import com.suma.pojo.TsInfoExample;
import com.suma.service.BaseService;
import com.suma.service.TsService;
import com.suma.utils.Insert;
import org.springframework.stereotype.Service;
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

    @Override
    public void checkExist(TsInfo tsInfo) {
        TsInfoExample example = new TsInfoExample();
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
}
