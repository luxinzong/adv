package com.suma.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.BaseException;
import com.suma.pojo.ChannelInfo;
import com.suma.pojo.ChannelInfoExample;
import com.suma.service.ChannelService;
import com.suma.utils.Insert;
import com.suma.utils.Result;
import com.suma.utils.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @auther: zhangzhaoyuan
 * @date: 2018/10/24
 * @description:
 */
@RestController
@RequestMapping("channel")
public class ChannelController extends BaseController {

    @Autowired
    private ChannelService channelService;

    @RequestMapping("query")
    public Result queryChannel(String channelId, String channelName, Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageSize == null) {
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);
        }

        ChannelInfoExample example = new ChannelInfoExample();
        ChannelInfoExample.Criteria criteria = example.createCriteria();
        if (channelId != null) {
            criteria = criteria.andChannelIdEqualTo(channelId);
        }
        if (channelName != null)
            criteria.andChannelNameEqualTo(channelName);
        PageHelper.startPage(pageNum, pageSize);
        List<ChannelInfo> channelInfos = channelService.selectByExample(example);

        return Result.success(new PageInfo<ChannelInfo>(channelInfos));
    }

    @RequestMapping("add")
    public Result addChannel(@Validated({Insert.class}) ChannelInfo channelInfo) {
        channelService.checkDuplicate(channelInfo);
        return toResult(channelService.save(channelInfo));
    }

    @RequestMapping("update")
    public Result updateChannel(@Validated({Update.class}) ChannelInfo channelInfo) {
        channelService.checkDuplicate(channelInfo);
        ChannelInfo oldInfo = channelService.findByPK(channelInfo.getId());
        oldInfo.setChannelId(channelInfo.getChannelId());
        oldInfo.setChannelName(channelInfo.getChannelName());
        oldInfo.setType(channelInfo.getType());
        return toResult(channelService.update(oldInfo));
    }

    @RequestMapping("delete")
    public Result deleteChannel(Long[] ids) {
        if (ids == null)
            throw new BaseException(ExceptionConstants.BASE_EXCEPTION_MISSING_PARAMETERS);

        for (Long id : ids) {
            channelService.deleteByPK(id);
        }

        return Result.success();
    }


}
