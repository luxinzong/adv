package com.suma.controller;

import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvFlyWordException;
import com.suma.pojo.AdvFlyWord;
import com.suma.pojo.AdvFlyWordExample;
import com.suma.service.AdvFlywordService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description 字幕广告属性增删改查
 */
@RestController
@RequestMapping(value = "flyword")
public class AdvFlywordController extends BaseController{

    @Autowired
    AdvFlywordService advFlywordService;

    /**
     * 插入字幕广告数据
     * @param advFlyWord
     * @return
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public Result insert(AdvFlyWord advFlyWord) {
        if (advFlyWord == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        return toResult(advFlywordService.save(advFlyWord));
    }

    /**
     * 删除字幕广告数据
     * @param id
     * @return
     */
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
     * 批量删除字幕广告数据
     * @param str
     * @return
     */
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public Result delete(String str) {
        Result result = new Result();
        String[] ids = str.substring(0,str.lastIndexOf(",")).split(",");
        StringBuilder stringBuilder = new StringBuilder();
        int count = 0;
        for (String id : ids) {
            if (advFlywordService.findByPK(Long.valueOf(id)) == null) {
                stringBuilder.append(id+",");
                count++;
                continue;
            }
            advFlywordService.deleteByPK(Long.valueOf(id));
        }
        if (count == ids.length) {
            return Result.success();
        } else {
            return  Result.error("ID为"+stringBuilder.toString()+"删除失败");
        }
    }

    /**
     * 更新字幕广告数据
     * @param advFlyWord
     * @return 字幕广告数据 AdvFlyWord
     */
    @RequestMapping(value = "update")
    public Result update(AdvFlyWord advFlyWord) {
        if (advFlyWord == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
       return toResult(advFlywordService.update(advFlyWord));
    }

    /**
     * 批量更新字幕广告数据
     * @param advFlyWords
     * @return List<AdvFlyWord> 字幕广告数据的集合
     */
    @RequestMapping(value = "updateAll")
    public Result update(List<AdvFlyWord> advFlyWords) {
        if (advFlyWords == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        for (AdvFlyWord advFlyWord : advFlyWords) {
            if (advFlywordService.findByPK(advFlyWord.getId()) == null) {
                throw new AdvFlyWordException("广告ID:" + advFlyWord.getId() + "不存在");
            }
            advFlywordService.update(advFlyWord);
        }
        return Result.success();
    }

    /**
     * 查询字幕广告对应的所有字幕信息
     * @param advInfoId
     * @return
     */
    @RequestMapping(value = "query")
    public Result query(Long advInfoId) {
        if (advInfoId == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWOR_REQUESTPARAM_IS_NULL);
        }
        AdvFlyWordExample example = new AdvFlyWordExample();
        example.createCriteria().andAdvInfoIdEqualTo(advInfoId);
        List<AdvFlyWord> advFlyWords = advFlywordService.selectByExample(example);
        if (advFlyWords == null) {
            throw new AdvFlyWordException(ExceptionConstants.ADV_FLYWORD_IS_NOT_EXIST);
        }
        PageInfo<AdvFlyWord> pageInfo = new PageInfo<>();
        pageInfo.setList(advFlyWords);
        pageInfo.setTotal(advFlyWords.size());
        return Result.success(pageInfo);
    }

}














