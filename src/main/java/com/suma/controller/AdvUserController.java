package com.suma.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.suma.constants.ExceptionConstants;
import com.suma.dto.AdvUserDto;
import com.suma.exception.UserException;
import com.suma.pojo.AdvUser;
import com.suma.service.iAdvUserService;
import com.suma.utils.Result;
import com.suma.vo.AdvUserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/19 0019
 * @Description 用户controller
 **/
@RestController
@RequestMapping("/system/user")
public class AdvUserController extends BaseController{
    @Autowired
    private iAdvUserService advUserService;

    /**
     * 添加角色信息
     * 设计多表操作，必须用事务进行处理
     *
     * @param advUserVO
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@Validated AdvUserVO advUserVO){
        AdvUser advUser = new AdvUser();
        BeanUtils.copyProperties(advUserVO,advUser);

        return toResult(advUserService.insertUser(advUser));
    }


    /**
     * 以get方式请求数据，直接返回角色默认列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result getAdvUserList(){
        return  list(null,null,null,
                null,null,1,10);
    }

    /**
     * 删除角色
     *
     * @param userId
     * @return
     */
    @PostMapping("/delete")
    public Result delete(Integer userId){
        //对参数进行验证
        if(userId == null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_USER_ID_IS_NULL);
        }

        return toResult(advUserService.deleteUserById(userId));
    }

    /**
     * 查询对应用户信息
     *
     * @param userName
     * @param phoneNumber
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/list")
    public Result list(String userName, String phoneNumber, String status,
                       String startTime, String endTime
                        , @RequestParam(value = "pageNum",defaultValue = "1")Integer pageNum,
                          @RequestParam(value = "pageSize",defaultValue = "10")Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        PageInfo<AdvUserDto> advUserDtoPageInfo = advUserService.selectUserList(userName,phoneNumber,status,
                                                                                        startTime,endTime);

        if(CollectionUtils.isEmpty(advUserDtoPageInfo.getList())){
            return Result.selectIsNullError();
        }

        return Result.success(advUserDtoPageInfo);
    }

    /**
     * 修改数据
     *
     * @param advUserVO
     * @param userId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/update")
    public Result update(@Validated AdvUserVO advUserVO,Integer userId){
        if(userId == null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_USER_ID_IS_NULL);
        }
        //生成AdvUser
        AdvUser advUser = new AdvUser();
        BeanUtils.copyProperties(advUserVO,advUser);
        advUser.setUserId(userId);

        return toResult(advUserService.updateUser(advUser));
    }

}
