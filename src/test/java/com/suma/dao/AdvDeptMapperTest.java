package com.suma.dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.suma.AdvApplicationTests;
import com.suma.pojo.AdvDept;
import com.suma.service.iAdvMenuService;
import com.suma.service.iAdvRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AdvDeptMapperTest extends AdvApplicationTests {


    @Autowired
    private AdvDeptMapper advDeptMapper;
    @Autowired
    private iAdvRoleService advRoleService;
    @Autowired
    private iAdvMenuService menuService;

    @Autowired
    private AdvFlyWordMapper advFlyWordMapper;

    @Test
    public void testMenu(){

    }


    @Test
    public void testRole(){

    }


    @Test
    public void testMultiMap(){



    }

    @Test
    public void insert() {

    }

    @Test
    public void testAns(){

    }

    @Test
    public void testMaxOrderNum(){

    }

    @Test
    public void selectAdvDeptCount() {

    }

    @Test
    public void checkAdvDeptExistUser() {

        int result = advDeptMapper.checkAdvDeptExistUser(1);
        System.out.println(result);
    }


    @Test
    public void selectAdvDeptAll() {
        List<AdvDept> advDeptList = advDeptMapper.selectAdvDeptAll();
        System.out.println(advDeptList);
    }

    @Test
    public void checkAdvDeptNameUnique() {

    }

    @Test
    public void deleteByPrimaryKey() {
        int result = advDeptMapper.deleteByPrimaryKey(1);
        System.out.println(result);
    }


    @Test
    public void insertSelective() {

    }

    @Test
    public void selectByPrimaryKey() {
        AdvDept advDept = advDeptMapper.selectByPrimaryKey(1);
        System.out.println(advDept);
    }

    @Test
    public void updateByPrimaryKeySelective() {


    }
    @Test
    public void updateByPrimaryKey() {
    }
}