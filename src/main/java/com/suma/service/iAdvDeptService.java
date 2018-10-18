package com.suma.service;

import com.suma.dto.AdvDeptDto;
import com.suma.pojo.AdvDept;

import java.util.List;
import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/12 0012
 * @Description 部门管理 服务层
 **/
public interface iAdvDeptService {

    /**
     * 查询部门管理数据
     *
     * @param advDept
     * @return 部门信息集合
     */
    public List<AdvDeptDto> selectAdvDeptList(AdvDept advDept);

    /**
     * 查询部门所有数据
     *
     * @return 部门信息集合
     */
    public List<AdvDept> selectAdvDeptAll();

    /**
     * 查询部门管理树
     *
     * @return 所有部门信息
     */
    public List<AdvDeptDto> selectAdvDeptTree();

    /**
     * 查询部门人数
     *
     * @param parentId
     * @return 结果
     */
    public int selectAdvDeptCount(Integer parentId);

    /**
     *新增保存部门信息
     *
     * @param advDept
     * @return 结果
     */
    public int insertAdvDept(AdvDept advDept);

    /**
     * 删除部门信息
     *
     * @param advDeptId
     * @return
     */
    public int deleteAdvDeptById(Integer advDeptId);



    /**
     * 修改保存部门信息
     *
     * @return 结果
     */
    public int updateAdvDept(AdvDept advDept);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId
     * @return 部门信息
     */
    public AdvDept selectAdvDeptById(Integer deptId);


    /**
     * 查看部门是否存在用户
     *
     * @param deptId
     * @return
     */
    public boolean checkAdvDeptExistUser(Integer deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName
     * @return boolean
     */
    public boolean checkAdvDeptNameUnique(String deptName);


    /**
     * 根据deptId查询当前部门最大的orderNum
     *
     * @param deptId
     * @return
     */
    public int getMaxAdvDeptOrderNum(Integer deptId);

    /**
     * 获取部门总数
     *
     * @return
     */
    public int getAdvDeptCount();

}

































