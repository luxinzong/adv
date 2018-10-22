package com.suma.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvDeptMapper;
import com.suma.dao.AdvUseRoleMapper;
import com.suma.dao.AdvUserMapper;
import com.suma.dto.AdvUserDto;
import com.suma.exception.UserException;
import com.suma.pojo.AdvDept;
import com.suma.pojo.AdvUser;
import com.suma.pojo.AdvUserRole;
import com.suma.service.iAdvUserService;
import com.suma.service.iPassWordService;
import com.suma.utils.ShiroUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description
 **/
@Service
public class AdvUserServiceImpl implements iAdvUserService {
    @Autowired
    private AdvUserMapper advUserMapper;
    @Autowired
    private AdvUseRoleMapper advUseRoleMapper;
    @Autowired
    private AdvDeptMapper advDeptMapper;
    @Autowired
    private iPassWordService passWordService;

    @Override
    public int insertUser(AdvUser advUser) {
        //检查用户名称是否已经存在
        String userName = advUser.getUserName();
        AdvUser checkAdvUser = advUserMapper.selectAdvUserByUserName(userName);
        if(checkAdvUser != null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_USER_NAME_IS_EXIST);
        }
        //todo 添加创建人
        //对密码进行加密
        String salt = ShiroUtils.generateRandomSalt();
        advUser.setSalt(salt);
        String password = passWordService.encryptPassword(userName,advUser.getPassword(),salt);
        advUser.setPassword(password);
        int insertRows = advUserMapper.insertSelective(advUser);
        //提交数据后获取对应存入对应用户id，以便增加对应角色id
        AdvUser tempAdvUser = advUserMapper.selectAdvUserByUserName(userName);
        advUser.setUserId(tempAdvUser.getUserId());
        int insertAdvUserRole = insertAdvUserRole(advUser);

        return insertRows + insertAdvUserRole;
    }

    /**
     * 新增用户角色信息
     *
     * @param advUser
     * @return
     */
    private int insertAdvUserRole(AdvUser advUser){
        int rows = 0;
        //新增用户和角色管理
        List<AdvUserRole> userRoleList = Lists.newArrayList();
        advUser.getRoleIds().forEach(roleId ->{
            AdvUserRole advUserRole = new AdvUserRole();
            advUserRole.setUserId(advUser.getUserId());
            advUserRole.setRoleId(roleId);
            userRoleList.add(advUserRole);
        });
        //进行添加操作
        if(userRoleList.size() > 0){
            rows = advUseRoleMapper.batchUserRole(userRoleList);
        }

        return rows;
    }

    /**
     * 查询用户信息
     *
     * @param userName
     * @param phoneNumber
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageInfo<AdvUserDto> selectUserList(String userName, String phoneNumber, String status, String startTime, String endTime) {
        List<AdvUser> advUserList = advUserMapper.selectUserList(userName,phoneNumber,status,startTime,endTime);
        List<AdvUserDto> advUserDtoList = Lists.newArrayList();
        if(!CollectionUtils.isEmpty(advUserList)){
            addRoleIdsFroAdvUser(advUserList);
            //进行bean属性赋值
            advUserList.forEach(advUser -> {
                AdvUserDto advUserDto = new AdvUserDto();
                BeanUtils.copyProperties(advUser,advUserDto);
                advUserDtoList.add(advUserDto);
            });
            //根据deptId拼装dept对象到advUserDto
            addDeptForAdvUserDto(advUserDtoList);
        }

        PageInfo<AdvUserDto> pageInfo = new PageInfo<>(advUserDtoList);
        return pageInfo;
    }

    /**
     * 给对应user添加roleIds
     *
     * @param advUserList
     */
    private void addRoleIdsFroAdvUser(List<AdvUser> advUserList){
        advUserList.forEach(advUser -> {
            List<Integer> roleIds = advUseRoleMapper.selectRoleIdsByUserId(advUser.getUserId());
            advUser.setRoleIds(roleIds);
        });
    }

    /**
     * 为AdvUserDto提供拼装dept对象
     */
    private void addDeptForAdvUserDto(List<AdvUserDto> advUserDtoList){
        advUserDtoList.forEach(advUserDto -> {
            Integer deptId = advUserDto.getDeptId();
            AdvDept advDept = advDeptMapper.selectByPrimaryKey(deptId);
            advUserDto.setAdvDept(advDept);
        });
    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    public int deleteUserById(Integer userId){
        //先判断删除id用户是否存在
        AdvUser advUser = advUserMapper.selectByPrimaryKey(userId);
        if(advUser == null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_ID_NOT_EXIST);
        }
        //删除对应用户信息
        int rows = advUserMapper.deleteByPrimaryKey(userId);
        int deleteRoleRows = deleteRolesByUserId(userId);

        return rows + deleteRoleRows;
    }

    /**
     * 通过用户id删除对应角色id
     *
     * @param userId
     * @return
     */
    private int deleteRolesByUserId(Integer userId){
        return advUseRoleMapper.deleteRoleIdsByUserId(userId);
    }

    /**
     * 修改用户信息
     *
     * @param advUser
     * @return
     */
    @Override
    public int updateUser(AdvUser advUser) {
        //先判断当前用户是否存在
        AdvUser selectAdvUser = advUserMapper.selectByPrimaryKey(advUser.getUserId());
        if(selectAdvUser == null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_ID_NOT_EXIST);
        }
        //对信息进行修改
        int updateRows = advUserMapper.updateByPrimaryKeySelective(advUser);
        //对应角色进行修改，先删除在添加
        int deleteRows = advUseRoleMapper.deleteRoleIdsByUserId(advUser.getUserId());
        //如果deleteRows > 0说明删除了数据，要进行添加数据
        int insertRows = 0;
        if(deleteRows > 0){
            insertRows = insertAdvUserRole(advUser);
        }

        return deleteRows + insertRows + updateRows;
    }
}
