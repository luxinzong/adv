package com.suma.constants;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 错误异常常量
 **/
public class ExceptionConstants {

    public static final String BASE_EXCEPTION_MISSING_PARAMETERS="缺少参数";

    //部门异常常量
    public static final String DEPT_EXCEPTION_DEPT_ID_ISNULL = "部门ID为空";
    public static final String DEPT_EXCEPTION_DEPT_NAME_IS_NULL_OR_EMPTY = "部门名称为空";
    public static final String DEPT_EXCEPTION_PARENT_ID_IS_NULL = "部门父类ID为空";
    public static final String DEPT_EXCEPTION_EXIST_NEXT_DEPT = "存在下级部门，不允许删除";
    public static final String DEPT_EXCEPTION_DEPT_EXIST_USER = "部门存在用户，不允许删除";
    public static final String DEPT_EXCEPTION_DEPT_EXIST_NAME = "部门名称已存在";
    public static final String DEPT_EXCEPTION_DEPT_ID_NOT_EXIST = "部门ID不存在";

    //菜单异常常量
    public static final String MENU_EXCEPTION_DEPT_ID_ISNULL = "菜单ID为空";
    public static final String MENU_EXCEPTION_DEPT_NAME_IS_NULL_OR_EMPTY = "菜单名称为空";
    public static final String MENU_EXCEPTION_PARENT_ID_IS_NULL = "菜单父类ID为空";
    public static final String MENU_EXCEPTION_EXIST_NEXT_DEPT = "存在下级菜单，不允许删除";
    public static final String MENU_EXCEPTION_DEPT_EXIST_NAME = "菜单名称已存在";
    public static final String MENU_EXCEPTION_DEPT_ID_NOT_EXIST = "菜单ID不存在";


    //广告信息常量
    public static final String INFO_EXCEPTION_QUERYPARAMS_IS_NULL = "缺少广告查询参数";
    public static final String INFO_EXCEPTION_MISSING_REQUIRED_PARAMS = "缺少必须参数";
    public static final String INFO_EXCEPTION_INFO_NAME_IS_EXIT = "广告名称已存在";
    public static final String INFO_EXCEPTION_INFO_IS_NOT_EXIT = "该广告不存在";
    public static final String DATE_FILL_IN_ERROR = "日期填写错误";
    public static final String INFO_EXCEPTION_DELETE_ERROR = "删除信息失败";
    public static final String INFO_EXCEPTION_ADV_TYPE_ID__NULL = "该广告类型ID不存在";

    //素材异常常量
    public static final String MATERIAL_EXCEPTION_UPLOAD_FAIL="素材上传失败";
    public static final String MATERIAL_EXCEPTION_UPDATE_FAIL="素材修改失败";
    public static final String MATERIAL_EXCEPTION_DELETE_FAIL="素材修改失败";

    //字幕广告异常常量
    public static final String ADV_FLYWORD_IS_NOT_EXIST = "字幕广告不存在";


    //角色异常常量
    public static final String ROLE_EXCEPTION_ROLE_ID_IS_NULL="角色ID为空";
    public static final String ROLE_EXCEPTION_EXIST_NAME="角色名称已存在";
    public static final String ROLE_EXCEPTION_ID_NOT_EXIST="角色id不存在";

    //广告信息对应资源异常
    public static final String INFO_MATERIAL_REQUESTPARAMS_IS_NULL = "缺少必须参数";
    public static final String INFO_MATERIAL_INFO_MATERIAL_IS_NULL = "没有广告资源";

}
