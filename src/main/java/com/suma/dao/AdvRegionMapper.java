package com.suma.dao;

import com.suma.pojo.AdvRegion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvRegionMapper {
    int deleteByPrimaryKey(Integer regionId);

    int insert(AdvRegion record);

    int insertSelective(AdvRegion record);

    AdvRegion selectByPrimaryKey(Integer regionId);

    int updateByPrimaryKeySelective(AdvRegion record);

    int updateByPrimaryKey(AdvRegion record);

    /**
     * 检查区域姓名是否唯一
     *
     * @param regionName
     * @return
     */
    int checkAdvRegionNameUnique(@Param("regionName")String regionName);

    /**
     * 根据deptId查询当前区域最大的orderNum
     *
     * @param parentId
     * @return
     */
    int getMaxAdvRegionOrderNum(@Param("parentId") Integer parentId);

    /**
     * 获取当前区域总数
     *
     * @return
     */
    int getAdvRegionCount();

    /**
     * 查询当前id包含子区域总数
     *
     * @param regionId
     * @return
     */
    int selectAdvCountByParentId(@Param("regionId") Integer regionId);

    /**
     * 通过条件查询区域列表
     *
     * @return
     */
    List<AdvRegion> selectAdvRegionList(AdvRegion advRegion);

    /**
     * 查询全部区域信息
     *
     * @return
     */
    List<AdvRegion> selectAdvRegionAll();




}