package com.suma.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvMenuMapper;
import com.suma.dto.AdvMenuDto;
import com.suma.exception.MenuException;
import com.suma.pojo.AdvMenu;
import com.suma.service.iAdvMenuService;
import com.suma.utils.AncestorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description
 **/
@Service
public class AdvMenuServiceImpl implements iAdvMenuService {

    @Autowired
    private AdvMenuMapper advMenuMapper;

    /**
     * 添加部门
     *
     * @param advMenu
     * @return
     */
    @Override
    public int insertAdvMenu(AdvMenu advMenu) {
        //判断菜单名称是否存在
        String menuName = advMenu.getMenuName();
        int result = advMenuMapper.checkAdvMenuUnique(menuName);
        if(result > 0){
             throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_EXIST_NAME);
        }
        //设置orderNum，如果为空的话取当前数据库中最大值+1
        Integer orderNum = advMenu.getOrderNum();
        if(orderNum == null){
            Integer parentId = advMenu.getParentId();
            //如果当前parentId为空，说明是一级菜单,直接查询parentId=0的最大orderNum
            if(parentId == null){
                parentId = 0;
            }

            int currentMaxOrderNum = advMenuMapper.getMaxAdvMenuOrderNum(parentId);
            int newOrderNum = currentMaxOrderNum + 1;
            advMenu.setOrderNum(newOrderNum);
        }
        //查询父类部门
        AdvMenu parentAdvMenu = advMenuMapper.selectByPrimaryKey(advMenu.getParentId());
        if(parentAdvMenu != null){
            //添加祖先部门ID
            advMenu.setAncestors(parentAdvMenu.getAncestors() + "," + advMenu.getParentId());
        }else{//当前部门没有父亲部门
            advMenu.setAncestors(AncestorUtil.ROOT);
        }
        //默认状态为显示
        advMenu.setStatus(CommonConstants.NORMAL_STATUS);
        //todo 登录工具添加用户名
        return advMenuMapper.insertSelective(advMenu);
    }

    /**
     * 修改部门信息
     *
     * @param advMenu
     * @return
     */
    @Override
    public int updateAdvMenu(AdvMenu advMenu) {
        //判断菜单id存在
        int advMenuId = advMenu.getMenuId();
        AdvMenu selectAdvMenu = advMenuMapper.selectByPrimaryKey(advMenuId);
        if(selectAdvMenu == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_NOT_EXIST);
        }
        //修改对应祖先对应数据
        AdvMenu parentAdvMenu = advMenuMapper.selectByPrimaryKey(advMenu.getParentId());
        if(parentAdvMenu == null){
            advMenu.setAncestors(AncestorUtil.ROOT);
        }else{
            advMenu.setAncestors(parentAdvMenu.getAncestors() + "," +advMenu.getParentId());
        }
        //todo 添加修改用户名
        return advMenuMapper.updateByPrimaryKeySelective(advMenu);
    }

    /**
     * 查询所有部门
     *
     * @return
     */
    @Override
    public List<AdvMenuDto> selectMenuAll() {
        //获取所有部门信息
        List<AdvMenu> advMenuList = advMenuMapper.selectAdvMenuAll();
        //进行排序
        Collections.sort( advMenuList,advMenuComparator );

        return produceAdvMenuDto(advMenuList);
    }

    /**
     * 通过条件查询菜单树
     *
     * @param advMenu
     * @return
     */
    @Override
    public List<AdvMenuDto> selectAdvMenuList(AdvMenu advMenu) {
        List<AdvMenu> menuList = advMenuMapper.selectAdvMenuList(advMenu);
        //进行拼装dto
        return produceAdvMenuDto(menuList);
    }
    /**
     * 查询菜单管理树
     * 所有状态
     * @return
     */
    public List<AdvMenuDto> selectMenuTree(){
        //先获取所有部门信息
        List<AdvMenu> menuList = advMenuMapper.selectAdvMenuAll();
        return produceAdvMenuTree(menuList,null);
    }

    /**
     * 有效菜单管理树
     */
    public List<AdvMenuDto> selectMenuTreeStatusIsValid(){
        //先获取所有部门信息
        List<AdvMenu> menuList = advMenuMapper.selectAdvMenuAll();
        return produceAdvMenuTree(menuList,CommonConstants.NORMAL_STATUS);
    }

    /**
     * 生产menuDto
     *
     * @param advMenuList
     * @return
     */
    private List<AdvMenuDto> produceAdvMenuDto(List<AdvMenu> advMenuList){
        if(CollectionUtils.isEmpty(advMenuList)){
            return null;
        }

        List<AdvMenuDto> advMenuDtoList = Lists.newArrayList();
        Map<Integer,String> parentNameMap = Maps.newConcurrentMap();
        advMenuList.forEach(advMenu -> {
            parentNameMap.put(advMenu.getMenuId(),advMenu.getMenuName());
        });
        advMenuList.forEach(advMenu -> {
            AdvMenuDto advMenuDto = AdvMenuDto.adapt(advMenu);
            //添加parentName
            if(advMenu.getParentId() == 0){
                advMenuDto.setParentName("无");
            }else{
                //获取当前父类名称
                String parentName = parentNameMap.get(advMenu.getParentId());
                advMenuDto.setParentName(parentName);
            }

            advMenuDtoList.add(advMenuDto);
        });

        return advMenuDtoList;
    }


    private List<AdvMenuDto> produceAdvMenuTree(List<AdvMenu> advMenuList,String status){
        //生产dto
        List<AdvMenuDto> menuDtoList = produceAdvMenuDto(advMenuList);
        return advMenuListToTree(menuDtoList,AncestorUtil.ROOT,status);
    }


    /**
     * 将menuList转化为树形结构
     *
     * @param menuDtoList
     * @param ancestor
     * @return
     */
    private List<AdvMenuDto> advMenuListToTree(List<AdvMenuDto> menuDtoList, String ancestor,String status){
        //如果menuDtoList为空，直接生成一个List
        if(CollectionUtils.isEmpty( menuDtoList )){
            return Lists.newArrayList();
        }

        //生成可以用一个key存储多个Value的安全map
        Multimap<String,AdvMenuDto> advMenuDtoMultimap = ArrayListMultimap.create();
        //生成rootList
        List<AdvMenuDto> rootList = Lists.newArrayList();
        //遍历menuDtoList
        menuDtoList.forEach( advMenuDto -> {
            //根据ancestor,存储对应menu对象
            if(Strings.isNullOrEmpty(status)){//如果status为null则不进行状态验证
                advMenuDtoMultimap.put( advMenuDto.getAncestors(),advMenuDto );
                if(advMenuDto.getAncestors().equals( ancestor )){
                    rootList.add( advMenuDto ); }
            }else{
                if(advMenuDto.getStatus().equals(status)){
                    advMenuDtoMultimap.put( advMenuDto.getAncestors(),advMenuDto );
                }
                if(advMenuDto.getAncestors().equals( ancestor ) && advMenuDto.getStatus().equals(status)){
                    rootList.add( advMenuDto ); }
            }

        });
        //root进行orderNum排序
        Collections.sort( rootList,advMenuDtoComparator );
        //拼装部门树
        transformAdvMenuTree( rootList,ancestor,advMenuDtoMultimap );
        return rootList;
    }

        private void transformAdvMenuTree(List<AdvMenuDto> advMenuDtoList,String ancestor,Multimap<String,AdvMenuDto> advMenuDtoMultimap){
        advMenuDtoList.forEach( advMenuDto -> {
            //获取下级ancestor
            String nextAncestor = AncestorUtil.calculateAncestor( ancestor,advMenuDto.getMenuId() );
            //获取下级list
            List<AdvMenuDto> tempAdvMenuDtoList = (List<AdvMenuDto>) advMenuDtoMultimap.get( nextAncestor );
            if(tempAdvMenuDtoList != null){
                //排序
                Collections.sort( tempAdvMenuDtoList,advMenuDtoComparator );
                //进行递归处理
                advMenuDto.setChildren( tempAdvMenuDtoList );
                transformAdvMenuTree( tempAdvMenuDtoList,nextAncestor,advMenuDtoMultimap );
            }
        } );
    }



    /**
     * AdvMenu按照orderNum顺序进行排序
     */
    private Comparator<AdvMenu> advMenuComparator = new Comparator<AdvMenu>() {
        @Override
        public int compare(AdvMenu o1, AdvMenu o2) {
            return o1.getOrderNum() - o2.getOrderNum();
        }
    };

    private Comparator<AdvMenuDto> advMenuDtoComparator = new Comparator<AdvMenuDto>() {
        @Override
        public int compare(AdvMenuDto o1, AdvMenuDto o2) {
            return o1.getOrderNum() - o2.getOrderNum();
        }
    };

    /**
     * 查询当前父id是否包含子菜单
     *
     * @param parentId
     * @return
     */
    @Override
    public int selectAdvMenuCountByParentId(Integer parentId) {
        return advMenuMapper.selectAdvMenuCountByParentId(parentId);
    }

    /**
     * 删除菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public int deleteMenuById(Integer menuId) {
        //判断当前id是否存在
        AdvMenu selectAdvMenu = advMenuMapper.selectByPrimaryKey(menuId);
        if(selectAdvMenu == null){
            throw new MenuException(ExceptionConstants.MENU_EXCEPTION_DEPT_ID_NOT_EXIST);
        }
        return advMenuMapper.deleteByPrimaryKey(menuId);
    }

}
