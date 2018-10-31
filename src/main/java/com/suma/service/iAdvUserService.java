package com.suma.service;

import com.github.pagehelper.PageInfo;
import com.suma.dto.AdvUserDto;
import com.suma.pojo.AdvUser;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description 用户服务
 **/
public interface iAdvUserService {

    /**
     * 添加用户
     *
     * @param advUser
     * @return
     */
    public int insertUser(AdvUser advUser);


    public PageInfo<AdvUserDto> selectUserList(String userName, String phoneNumber, String status,
                                               String startTime, String endTime);


    public int deleteUserById(Integer userId);

    public int deleteUserByIds(List<Integer> ids);

    public int updateUser(AdvUser advUser);

    public int updateUserPassword(String oldPassword,String newPassword);


    public AdvUser selectByUsername(String userName);

}
