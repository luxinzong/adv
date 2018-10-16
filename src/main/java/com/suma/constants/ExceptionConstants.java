package com.suma.constants;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/15 0015
 * @Description 错误异常常量
 **/
public class ExceptionConstants {

    //部门异常常量
    public static final String DEPT_EXCEPTION_DEPT_ID_ISNULL= "部门ID为空";
    public static final String DEPT_EXCEPTION_DEPT_NAME_IS_NULL_OR_EMPTY="部门名称为空";
    public static final String DEPT_EXCEPTION_PARENT_ID_IS_NULL="部门父类ID为空";
    public static final String DEPT_EXCEPTION_EXIST_NEXT_DEPT="存在下级部门，不允许删除";
    public static final String DEPT_EXCEPTION_DEPT_EXIST_USER="部门存在用户，不允许删除";
    public static final String DEPT_EXCEPTION_DEPT_EXIST_NAME="部门名称已存在";
    public static final String DEPT_EXCEPTION_DEPT_ID_NOT_EXIST="部门ID不存在";

    //菜单异常常量
    public static final String MENU_EXCEPTION_DEPT_ID_ISNULL= "菜单ID为空";
    public static final String MENU_EXCEPTION_DEPT_NAME_IS_NULL_OR_EMPTY="菜单名称为空";
    public static final String MENU_EXCEPTION_PARENT_ID_IS_NULL="菜单父类ID为空";
    public static final String MENU_EXCEPTION_EXIST_NEXT_DEPT="存在下级菜单，不允许删除";
    public static final String MENU_EXCEPTION_DEPT_EXIST_NAME="菜单名称已存在";
    public static final String MENU_EXCEPTION_DEPT_ID_NOT_EXIST="菜单ID不存在";




}
