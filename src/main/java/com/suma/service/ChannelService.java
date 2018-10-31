package com.suma.service;

import com.suma.pojo.ChannelInfo;
import com.suma.pojo.ChannelInfoExample;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/24
 * @description:
 */
public interface ChannelService extends BaseService<ChannelInfo, ChannelInfoExample, Long> {
    void checkDuplicate(ChannelInfo channelInfo);

    String findALLChannelIds();
}
