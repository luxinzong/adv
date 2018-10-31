package com.suma.service.impl;

import com.suma.dao.ChannelInfoMapper;
import com.suma.dao.ServiceInfoGroupMapper;
import com.suma.exception.BaseException;
import com.suma.pojo.ChannelInfo;
import com.suma.pojo.ChannelInfoExample;
import com.suma.pojo.ServiceInfoGroupExample;
import com.suma.service.BaseService;
import com.suma.service.ChannelService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/24
 * @description:
 */
@Service
public class ChannelServiceImpl extends BaseServiceImpl<ChannelInfo, ChannelInfoExample, Long> implements ChannelService {
    @Resource
    public void setBaseDao(ChannelInfoMapper baseDao) {
        super.setBaseDao(baseDao);
    }

    @Autowired
    private ServiceInfoGroupMapper serviceInfoGroupMapper;
    @Autowired
    private ChannelInfoMapper channelInfoMapper;

    @Override
    public void checkDuplicate(ChannelInfo channelInfo) {
        ChannelInfoExample example = new ChannelInfoExample();
        if (channelInfo.getId() != null) {
            ChannelInfo oldInfo = findByPK(channelInfo.getId());
            if (channelInfo.getChannelId().equals(oldInfo.getChannelId())
                    && channelInfo.getChannelName().equals(oldInfo.getChannelName())) {
                return;
            } else if (channelInfo.getChannelId().equals(oldInfo.getChannelId())) {
                example.createCriteria().andChannelNameEqualTo(channelInfo.getChannelName());
                List<ChannelInfo> channelInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(channelInfos)) {
                    throw new BaseException("该ChannelName已存在");
                }
                return;
            } else if (channelInfo.getChannelName().equals(oldInfo.getChannelName())) {
                example.createCriteria().andChannelIdEqualTo(channelInfo.getChannelId());
                List<ChannelInfo> channelInfos = selectByExample(example);
                if (!CollectionUtils.isEmpty(channelInfos)) {
                    throw new BaseException("该ChannelId已存在");
                }
                return;
            }
        }


        example.or().andChannelIdEqualTo(channelInfo.getChannelId());
        List<ChannelInfo> channelInfos = selectByExample(example);
        if (channelInfos != null && channelInfos.size() > 0) {
            throw new BaseException("该ChannelId已存在");
        }

        example.clear();

        example.or().andChannelNameEqualTo(channelInfo.getChannelName());
        List<ChannelInfo> channelInfos1 = selectByExample(example);
        if (channelInfos1 != null && channelInfos1.size() > 0) {
            throw new BaseException("该ChannelName已存在");
        }
    }

    @Override
    public String findALLChannelIds() {
        List<String> channelIds = channelInfoMapper.selectChannelIds();
        return StringUtils.join(channelIds, ",");
    }

    @Transactional
    @Override
    public int deleteByPK(Long id) {
        ServiceInfoGroupExample example = new ServiceInfoGroupExample();
        example.createCriteria().andSidEqualTo(id);
        serviceInfoGroupMapper.deleteByExample(example);
        return super.deleteByPK(id);
    }
}
