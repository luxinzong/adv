package com.suma.service;

import com.suma.dto.AdvRegionDto;
import com.suma.pojo.AdvRegion;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/26 0026
 * @Description
 **/
public interface iAdvRegionService {

   public int insertAdvRegion(AdvRegion advRegion);

   public int deleteAdvRegion(Integer regionId);

   public int updateAdvRegion(AdvRegion advRegion);

   public int getAdvRegionCount();

   public AdvRegion selectAdvRegionById(Integer regionId);

   public List<AdvRegionDto> selectAdvRegionList(AdvRegion advRegion);

   public List<AdvRegion> selectAdvRegionAll();

   public List<AdvRegionDto> selectAdvRegionTree();



}
