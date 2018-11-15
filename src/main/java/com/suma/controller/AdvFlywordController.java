package com.suma.controller;

import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvFlyWordException;
import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import com.suma.pojo.InfoFlyWordExample;
import com.suma.service.AdvFlywordService;
import com.suma.service.InfoFlywordService;
import com.suma.utils.Result;
import com.suma.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description 字幕模板 增查接口
 */
@RestController
@RequestMapping(value = "flyword")
public class AdvFlywordController extends BaseController{

    private static Logger logger = LoggerFactory.getLogger(AdvFlywordController.class);

    @Autowired
    AdvFlywordService advFlywordService;

    @Autowired
    InfoFlywordService infoFlywordService;

    /**
     * 插入字幕模板数据
     * @param advFlyWord
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(AdvFlyWord advFlyWord) {
        logger.debug(advFlyWord.toString());
        if (advFlyWord == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        //TODO 在创建广告时 模板添加 ID返回问题需要解决
        return toResult(advFlywordService.save(advFlyWord));
    }

    /**
     * 删除字幕模板数据
     * @param id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Result delete(Long id) {
        if (id == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        if (advFlywordService.findByPK(id) == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWORD_IS_NOT_EXIST);
        }
        return toResult(advFlywordService.deleteByPK(id));
    }

    /**
     * 更新字幕模板数据
     * @param advFlyWord
     * @return 字幕广告数据 AdvFlyWord
     */
    @RequestMapping(value = "update")
    public Result update(AdvFlyWord advFlyWord) {
        logger.debug(advFlyWord.toString());
        if (advFlyWord.getId() == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        return toResult(advFlywordService.updateByPrimaryKeySelective(advFlyWord));
    }

    /**
     * 查询字幕广告对应的字幕信息
     * @param
     * @return
     */
    @RequestMapping(value = "queryById")
    public Result query(Long id) {
        if (id == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        AdvFlyWord advFlyWord = advFlywordService.findByPK(id);
        if (advFlyWord == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWORD_IS_NOT_EXIST);
        }
        return Result.success(advFlyWord);
    }

    /**
     * 查询所有字幕广告
     * @return
     */
    @RequestMapping("queryAll")
    public Result queryAll() {
        List<AdvFlyWord> advFlyWords = advFlywordService.findALL();
        return Result.success(advFlyWords);
    }

}














