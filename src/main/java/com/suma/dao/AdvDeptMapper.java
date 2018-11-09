package com.suma.dao;

import com.suma.pojo.AdvDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/12
 * @Description 部门管理 数据层
 **/
public interface AdvDeptMapper {

    /**
     * 查询部门通过父类id
     *
     * @param parentId
     * @return 结果
     */
    int selectAdvDeptCount(@Param("parentId") Integer parentId);

    /**
     * 查询部门是否存在用户
     * @param deptId
     * @return 结果
     */
    int checkAdvDeptExistUser(@Param("deptId") Integer deptId);

    /**
     * 查询部门管理数据
     *
     * @param advDept 部门信息
     * @return 部门信息集合
     */
    List<AdvDept> selectAdvDeptList(AdvDept advDept);

    List<AdvDept> selectAdvDeptChildByParentId(Integer parentId);

    /**
     * 查询部门所有数据
     *
     * @return 部门信息集合
     */
    List<AdvDept> selectAdvDeptAll();

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @return
     */
    int checkAdvDeptNameUnique(@Param("deptName") String deptName);

    int checkAdvDeptNameUniqueInOthers(@Param("deptId")Integer deptId,@Param("deptName") String deptName);

    /**
     * 删除部门信息
     *
     * @param deptId
     * @return 结果
     */
    int deleteByPrimaryKey(Integer deptId);

    /**
     * 添加部门信息
     * @param record
     * @return 结果
     */

    int insert(AdvDept record);

    /**
     * 添加部门信息 对null判断
     * @param record
     * @return 结果
     */
    int insertSelective(AdvDept record);

    /**
     * 根据部门ID查询部门信息
     *
     * @param deptId
     * @return
     */
    AdvDept selectByPrimaryKey(Integer deptId);

    /**
     * 修改部门信息 对null判断
     * @param record
     * @return 结果
     */
    int updateByPrimaryKeySelective(AdvDept record);

    int batchUpdateAncestors(List<AdvDept> advDeptList);

    /**
     * 修改部门信息
     * @param record
     * @return 结果
     */
    int updateByPrimaryKey(AdvDept record);

    /**
     * 根据deptId查询当前部门最大的orderNum
     *
     * @param parentId
     * @return
     */
    int getMaxAdvDeptOrderNum(@Param("parentId") Integer parentId);


    int getAdvDeptCount();

    List<String> getAncestorList();

}