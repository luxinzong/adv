package com.suma.controller;

import com.suma.pojo.AdvFlyWord;
import com.suma.service.AdvFlywordService;
import com.suma.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @auther: luxinzong
 * @date: 2018/10/22 0022
 * @description
 */
@RestController
@RequestMapping(value = "flyword")
public class AdvFlywordController extends BaseController{

    @Autowired
    AdvFlywordService advFlywordService;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public Result insert(AdvFlyWord advFlyWord) {
        System.out.println(advFlyWord);
        return toResult(advFlywordService.save(advFlyWord));
    }
}














