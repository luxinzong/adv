package com.suma.dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.suma.AdvApplicationTests;
import com.suma.pojo.AdvDept;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AdvDeptMapperTest extends AdvApplicationTests {

    @Autowired
    private AdvDeptMapper advDeptMapper;

    @Test
    public void testMultiMap(){
        Multimap<String,Integer> multimap = ArrayListMultimap.create();
        multimap.put("1",1);
        multimap.put("1",2);
        System.out.println(multimap.get("1"));


    }


    @Test
    public void testAns(){
        List<String> ancestorList = advDeptMapper.getAncestorList();
        //过滤包含，的节点
        List<String> filterAncestorList = ancestorList.stream().filter(node -> !node.contains(","))
                .collect(Collectors.toList());
        System.out.println(filterAncestorList);
    }

    @Test
    public void testMaxOrderNum(){
        List<String> list = advDeptMapper.getAncestorList();
        System.out.println(list);
    }

    @Test
    public void selectAdvDeptCount() {
        int count = advDeptMapper.selectAdvDeptCount(1);

        System.out.println(count);
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
        int result = advDeptMapper.checkAdvDeptNameUnique("测试部门2");
        System.out.println(result);
    }

    @Test
    public void deleteByPrimaryKey() {
        int result = advDeptMapper.deleteByPrimaryKey(1);
        System.out.println(result);
    }


    @Test
    public void insertSelective() {
        AdvDept advDept = new AdvDept();
        advDept.setDeptName("测试部门2");
        advDept.setParentId(0);
        advDept.setOrderNum(2);
        advDept.setPhoneNumber("13222111122");
        advDept.setAncestors("0");
        advDept.setStatus("0");
        advDept.setLeader("hammy");

        advDeptMapper.insertSelective(advDept);
    }

    @Test
    public void selectByPrimaryKey() {
        AdvDept advDept = advDeptMapper.selectByPrimaryKey(1);
        System.out.println(advDept);
    }

    @Test
    public void updateByPrimaryKeySelective() {

        AdvDept advDept = new AdvDept();
        advDept.setDeptId(2);
        advDept.setDeptName("测试部门3");
        advDept.setParentId(0);
        advDept.setOrderNum(2);
        advDept.setPhoneNumber("13222111122");
        advDept.setAncestors("0");
        advDept.setStatus("0");
        advDept.setLeader("hammy");

        advDeptMapper.updateByPrimaryKeySelective(advDept);
    }
    @Test
    public void updateByPrimaryKey() {
    }
}