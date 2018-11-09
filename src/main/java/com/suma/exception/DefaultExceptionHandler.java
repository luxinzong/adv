package com.suma.exception;


import com.suma.constants.ExceptionConstants;
import com.suma.pojo.AdvResponseVO;
import com.suma.utils.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.tomcat.util.http.parser.Authorization;
import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 自定义异常处理
 **/
@RestControllerAdvice
public class DefaultExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(DeptException.class)
    public Result deptException(DeptException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AdvLocationException.class)
    public Result advLocationException(AdvLocationException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(MenuException.class)
    public Result menuException(MenuException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(RoleException.class)
    public Result roleException(RoleException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AdvMaterialException.class)
    public Result advMaterialException(AdvMaterialException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }


    @ExceptionHandler(AdvCheckException.class)
    public Result advCheckException(AdvCheckException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AdvFlyWordException.class)
    public Result advFlyWordException(AdvFlyWordException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AdvInfoException.class)
    public Result advInfoException(AdvInfoException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(InfoMaterialException.class)
    public Result infoMaterialException(InfoMaterialException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(UserException.class)
    public Result userException(UserException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public Result authorizationException(AuthorizationException e) {
        log.error(e.getMessage(), e);
        return Result.error(ExceptionConstants.NO_MENU_PERMISSION);
    }

    @ExceptionHandler(LoginException.class)
    public Result loginException(LoginException e) {
        log.error(e.getMessage(), e);
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(RegionException.class)
    public Result regionException(RegionException e){
        log.error(e.getMessage(),e);
        return Result.error(e.getMessage());
    }


    @ExceptionHandler(AdvRequestException.class)
    public AdvResponseVO advRequestException(AdvRequestException e) {
        if (e.getOriginException() != null)
            log.error(e.getOriginException().getMessage(), e.getOriginException());
        log.error(e.getMessage(), e);
        AdvResponseVO responseVO = new AdvResponseVO();
        responseVO.setSessionId(e.getSessionId());
        responseVO.setResultCode("1");
        responseVO.setResultCount(0L);
        responseVO.setResultDesc(e.getMessage());
        return responseVO;
    }


    /**'
     * 通过form表单传递参数异常
     *
     * @param bindException
     * @return
     */

    /**
     * '
     * 通过form表单传递参数异常
     *
     * @param bindException
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException bindException) {
        log.error(bindException.getMessage(), bindException);
        List<FieldError> fieldErrors = bindException.getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            stringBuilder.append(fieldError.getField() + fieldError.getDefaultMessage())
                    .append(",");
        });
        return Result.error(stringBuilder.toString());
    }

    /**
     * 通过json传递，出现参数错误异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage(), exception);
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        StringBuilder stringBuilder = new StringBuilder();
        fieldErrors.forEach(fieldError -> {
            stringBuilder.append(fieldError.getField() + fieldError.getDefaultMessage())
                    .append(",");
        });
        return Result.error(stringBuilder.toString());
    }

    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e) {
        log.error(e.getMessage());
        return Result.error(e.getMessage());
    }


}
