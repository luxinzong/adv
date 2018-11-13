package com.suma.utils;

import com.suma.service.impl.AdvRegionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @auther: luxinzong
 * @date: 2018/11/2 0002
 * @description
 */
public class RegionUtils {

    @Autowired
    private static AdvRegionService advRegionService;

    public static List<String> addRegionName(List<Integer> ids) {
        List<String> regionNames = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ids)) {
            ids.forEach(regionId->{
                regionNames.add(advRegionService.selectAdvRegionById(regionId).getRegionName());
            });
        }
        return regionNames;
    }

}
