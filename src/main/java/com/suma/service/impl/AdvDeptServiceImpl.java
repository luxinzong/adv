package com.suma.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvDeptMapper;
import com.suma.dto.AdvDeptDto;
import com.suma.exception.DeptException;
import com.suma.pojo.AdvDept;
import com.suma.pojo.AdvUser;
import com.suma.service.iAdvDeptService;
import com.suma.utils.AncestorUtil;
import com.suma.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/12 0012
 * @Description 部门管理 服务实现
 **/
@Service
public class AdvDeptServiceImpl implements iAdvDeptService {

    @Autowired
    private AdvDeptMapper advDeptMapper;
    @Autowired
    private TreeService<AdvDeptDto> treeService;

    @Override
    public List<AdvDept> selectAdvDeptList(AdvDept advDept) {
        List<AdvDept> advDeptList = advDeptMapper.selectAdvDeptList(advDept);
        return advDeptList;

    }
    @Override
    public List<AdvDeptDto> selectAdvDeptAll() {
        List<AdvDept> advDeptList = advDeptMapper.selectAdvDeptAll();
        if(!CollectionUtils.isEmpty(advDeptList)){
            Collections.sort(advDeptList,advDeptComparator);
        }

        return produceAdvDeptDto(advDeptList);
    }

    /**
     * 查询部门管理树
     *
     * @return
     */
    @Override
    public List<AdvDeptDto> selectAdvDeptTree() {
        //先获取全部部门信息
        List<AdvDept> deptList = advDeptMapper.selectAdvDeptAll();
        //进行拼装部门树
        return CollectionUtils.isEmpty(deptList)?null:produceAdvDeptTree(deptList,AncestorUtil.ROOT,null);
    }

    /**
     * 查询有效状态树
     *
     * @return
     */
    @Override
    public List<AdvDeptDto> selectDeptTreeStatusIsValid() {
        List<AdvDept> deptList = advDeptMapper.selectAdvDeptAll();
        return produceAdvDeptTree(deptList,AncestorUtil.ROOT,CommonConstants.NORMAL_STATUS);
    }

    /**
     * 生产DeptDto
     *
     * @return
     */
    private List<AdvDeptDto> produceAdvDeptDto(List<AdvDept> advDeptList){
        if(CollectionUtils.isEmpty(advDeptList)){
            return null;
        }
        List<AdvDeptDto> advDeptDtoList = Lists.newArrayList();
        Map<Integer,String> parentNameMap = Maps.newConcurrentMap();
        advDeptList.forEach(dept ->{
            parentNameMap.put(dept.getDeptId(),dept.getDeptName());
        });
        advDeptList.forEach(dept ->{
            AdvDeptDto advDeptDto = AdvDeptDto.adapt(dept);
            //添加parentName
            if(dept.getParentId() == 0){//说明当前是父类id
                advDeptDto.setParentName("无");
            }else{
                //获取当前父类名称
                String parentName = parentNameMap.get(dept.getParentId());
                advDeptDto.setParentName(parentName);
            }
            advDeptDtoList.add(advDeptDto);
        });

        return advDeptDtoList;
    }

    private List<AdvDeptDto> produceAdvDeptTree(List<AdvDept> advDeptList,String ancestor,String status){
        //对部门id和部门名称进行存储以便后续拼装使用
        //生成dtoList
        List<AdvDeptDto> dtoList = produceAdvDeptDto(advDeptList);
        return advDeptListToTree(dtoList,ancestor,status);
    }

    private List<AdvDeptDto> advDeptListToTree(List<AdvDeptDto> deptDtoList,String ancestor,String status){
        //如果deptDtoList为空，直接新生成一个list
        if(CollectionUtils.isEmpty(deptDtoList)){
            return Lists.newArrayList();
        }
        //生成线程安全的不可变map存储，ancestor
        Multimap<String,AdvDeptDto> ancestorMutimap = ArrayListMultimap.create();
        //生成rootList
        List<AdvDeptDto> rootList = Lists.newArrayList();
        treeService.addRootToRootList(deptDtoList,rootList,ancestor,status,ancestorMutimap);
        //对root按照从大到小排序
        Collections.sort(rootList,advDeptDtoComparator);
        //拼装部门树
        transformAdvDeptTree(rootList,ancestor,ancestorMutimap);
        return rootList;
    }

    private void transformAdvDeptTree(List<AdvDeptDto> advDeptDtoList,String ancestor,Multimap<String,AdvDeptDto> ancestorMutiMap){
        advDeptDtoList.forEach(advDeptDto -> {
            //获取下级ancestor
            String nextAncestor = AncestorUtil.calculateAncestor(ancestor,advDeptDto.getDeptId());
            List<AdvDeptDto> tempAdvDeptList = (List<AdvDeptDto>) ancestorMutiMap.get(nextAncestor);
            if(tempAdvDeptList != null){
                //排序
                Collections.sort(tempAdvDeptList,advDeptDtoComparator);
                //进行下一层处理
                advDeptDto.setChildren(tempAdvDeptList);
                transformAdvDeptTree(tempAdvDeptList,nextAncestor,ancestorMutiMap);
            }
        });

    }
    private Comparator<AdvDept> advDeptComparator = new Comparator<AdvDept>() {
        @Override
        public int compare(AdvDept o1, AdvDept o2) {
            return o1.getOrderNum() - o2.getOrderNum();
        }
    };
    private Comparator<AdvDeptDto> advDeptDtoComparator = new Comparator<AdvDeptDto>() {
        @Override
        public int compare(AdvDeptDto o1, AdvDeptDto o2) {
            return o1.getOrderNum() - o2.getOrderNum();
        }

    };

    @Override
    public int selectAdvDeptCount(Integer parentId) {
        if(parentId == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_ISNULL);
        }

        return advDeptMapper.selectAdvDeptCount(parentId);
    }

    /**
     * 添加新用户
     *
     * @param advDept
     * @return
     */
    @Override
    public int insertAdvDept(AdvDept advDept) {
        //判断部门姓名是否存在
        String deptName = advDept.getDeptName();
        int result = advDeptMapper.checkAdvDeptNameUnique(deptName);
        if(result > 0){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_EXIST_NAME);
        }
        //设置orderNum，如果为空的话取当前数据库中最大值+1
        Integer orderNum = advDept.getOrderNum();
        if(orderNum == null){
            Integer parentId = advDept.getParentId();
            if(parentId == null){
                parentId = 0;
            }
            int currentMaxOrderNum = advDeptMapper.getMaxAdvDeptOrderNum(parentId);
            int newOrderNum = currentMaxOrderNum + 1;
            advDept.setOrderNum(newOrderNum);
        }
        //查询父类部门
        AdvDept parentAdvDept = advDeptMapper.selectByPrimaryKey(advDept.getParentId());
        if(parentAdvDept != null){
            //添加祖先部门ID
            advDept.setAncestors(parentAdvDept.getAncestors() + "," + advDept.getParentId());
        }else{//当前部门没有父部门
            advDept.setAncestors(AncestorUtil.ROOT);
        }
        advDept.setCreateBy(ShiroUtils.getUser().getUserName());
        return advDeptMapper.insertSelective(advDept);
    }

    @Override
    public int deleteAdvDeptById(Integer advDeptId) {
        AdvDept advDept = advDeptMapper.selectByPrimaryKey(advDeptId);
        if(advDept == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_NOT_EXIST);
        }

        return advDeptMapper.deleteByPrimaryKey(advDeptId);
    }

    @Override
    public boolean checkAdvDeptExistUser(Integer deptId) {
        if(deptId == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_ISNULL);
        }

        return advDeptMapper.checkAdvDeptExistUser(deptId) > 0? true:false;
    }

    @Override
    public int updateAdvDept(AdvDept advDept) {
       //判断部门id存在
        int deptId = advDept.getDeptId();
        AdvDept selectAdvDept = advDeptMapper.selectByPrimaryKey(deptId);
        if(selectAdvDept == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_NOT_EXIST);
        }
        //判断修改姓名是否重复
        int existRepeatDeptName = advDeptMapper.checkAdvDeptNameUniqueInOthers(deptId,advDept.getDeptName());
        if(existRepeatDeptName > 0){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_EXIST_NAME);
        }
        //修改对应祖先数据
        AdvDept parentAdvDept = advDeptMapper.selectByPrimaryKey(advDept.getParentId());
        if(parentAdvDept == null){
            advDept.setAncestors(AncestorUtil.ROOT);
        }else if(parentAdvDept.getAncestors().length() > selectAdvDept.getAncestors().length()){//判断修改的父类部门与新部门是否出现跨层修改
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_NOT_ALLOW_CROSS_LAYER);
        }else{
            advDept.setAncestors(parentAdvDept.getAncestors() + "," + advDept.getParentId());
        }

        updateChildrenByParentId(advDept);
        advDept.setUpdateBy(ShiroUtils.getUser().getUserName());
        return advDeptMapper.updateByPrimaryKeySelective(advDept);
    }

    private void updateChildrenByParentId(AdvDept advDept){
        List<AdvDept> childAdvDept = advDeptMapper.selectAdvDeptChildByParentId(advDept.getDeptId());
        if(CollectionUtils.isEmpty(childAdvDept)){
            return;
        }else{
            childAdvDept.forEach(element ->{
                element.setAncestors(advDept.getAncestors() + "," + element.getParentId());
            });
            advDeptMapper.batchUpdateAncestors(childAdvDept);
            childAdvDept.forEach(element ->{
                updateChildrenByParentId(element);
            });
            return;
        }
    }



    @Override
    public AdvDept selectAdvDeptById(Integer deptId) {
        if(deptId == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_ID_ISNULL);
        }
        return advDeptMapper.selectByPrimaryKey(deptId);
    }

    @Override
    public boolean checkAdvDeptNameUnique(String deptName) {
        if(StringUtils.isEmpty(deptName)){
           throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_DEPT_NAME_IS_NULL_OR_EMPTY);
        }

        return advDeptMapper.checkAdvDeptNameUnique(deptName) == 1 ? true:false;
    }

    @Override
    public int getMaxAdvDeptOrderNum(Integer parentId) {
        if(parentId == null){
            throw new DeptException(ExceptionConstants.DEPT_EXCEPTION_PARENT_ID_IS_NULL);
        }

        return advDeptMapper.getMaxAdvDeptOrderNum(parentId);
    }

    @Override
    public int getAdvDeptCount() {
        return advDeptMapper.getAdvDeptCount();
    }
}
