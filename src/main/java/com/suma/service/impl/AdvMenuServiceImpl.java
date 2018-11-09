package com.suma.service.impl;

import com.google.common.base.Strings;
import com.google.common.collect.*;
import com.suma.constants.CommonConstants;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.*;
import com.suma.dto.AdvMenuDto;
import com.suma.dto.AdvPermsDto;
import com.suma.exception.MenuException;
import com.suma.exception.UserException;
import com.suma.pojo.AdvMenu;
import com.suma.pojo.AdvUser;
import com.suma.service.iAdvMenuService;
import com.suma.utils.AncestorUtil;
import com.suma.utils.CommonUtils;
import com.suma.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/16 0016
 * @Description
 **/
@Service
public class AdvMenuServiceImpl implements iAdvMenuService {

    @Autowired
    private AdvMenuMapper advMenuMapper;
    @Autowired
    private AdvUseRoleMapper advUseRoleMapper;
    @Autowired
    private AdvRoleMenuMapper advRoleMenuMapper;
    @Autowired
    private AdvUserMapper advUserMapper;

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
        advMenu.setCreateBy(ShiroUtils.getUser().getUserName());
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
        advMenu.setUpdateBy(ShiroUtils.getUser().getUserName());
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

    public Set<String> selectMenuPermsByUserId(Integer userId){
        //通过userId查询对应角色ids
        List<Integer> roleIds = advUseRoleMapper.selectRoleIdsByUserId(userId);
        if(CollectionUtils.isEmpty(roleIds)){
            return null;
        }

        List<Integer> menuIds = Lists.newArrayList();
        roleIds.forEach(roleId ->{
            List<Integer> tempMenuIds = advRoleMenuMapper.selectMenuIdsByAdvRoleId(roleId);
            tempMenuIds.forEach(tempMenuId ->{
                menuIds.add(tempMenuId);
            });
        });
        //通过角色id查询菜单权限
        List<String> menuPerms = advMenuMapper.selectAdvMenuPerms(menuIds);
        Set<String> permsSet = Sets.newHashSet(menuPerms);

        return permsSet;
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

    @Override
    public boolean checkRoleInMenuByMenuId(Integer menuId) {
        int rows = advRoleMenuMapper.selectCountRoleMenuByMenuId(menuId);

        return rows>0?true:false;

    }

    /**
     * 通过用户id查询对应权限列表
     *
     * @param username
     * @return
     */
    @Override
    public List<AdvPermsDto> selectMenuTreeByUserName(String username) {
        //判断是否存在用户id
        AdvUser advUser = advUserMapper.selectAdvUserByUserName(username);
        if(advUser == null){
            throw new UserException(ExceptionConstants.USER_EXCEPTION_ID_NOT_EXIST);
        }
        //通过用户id查询对应角色id
        List<Integer> advRoleList = advUseRoleMapper.selectRoleIdsByUserId(advUser.getUserId());
        if(CollectionUtils.isEmpty(advRoleList)){//说明用户没有角色
            return null;
        }
        //通过角色id查询对应权限树
        List<List<AdvMenuDto>> advMenuDtoList = Lists.newArrayList();
        advRoleList.forEach(advRoleId ->{
            //查询角色对应的菜单ids
            List<Integer> menuIds = advRoleMenuMapper.selectMenuIdsByAdvRoleId(advRoleId);
            //对菜单依次遍历
            List<AdvMenu> advMenuList = Lists.newArrayList();
            menuIds.forEach(menuId -> {
                AdvMenu advMenu = advMenuMapper.selectByPrimaryKey(menuId);
                advMenuList.add(advMenu);
            });
            //生成对应menuDto
            List<AdvMenuDto> menuDto = produceAdvMenuTree(advMenuList,CommonConstants.NORMAL_STATUS);
            advMenuDtoList.add(menuDto);
        });


        return produceAdvPermsList(advMenuDtoList);
    }

    /**
     * 生成权限列表
     *
     * @return
     */
    private List<AdvPermsDto> produceAdvPermsList(List<List<AdvMenuDto>> advMenuDtos){//todo 代码优化
        if(CollectionUtils.isEmpty(advMenuDtos)){
            return null;
        }
        //设计思路是menudto分为四层,permsDto只分为两层，这两层第一层是目录，第二层按钮中view的部分
        //所以用一个map进行存储，key为第一层的名称，value对应的view名称
        //迭代全部menudto，然后维护一个map进行检测当前是否保存具体view
        Map<String,AdvPermsDto> saveMap = Maps.newConcurrentMap();
        Multimap<String,String> multimap = ArrayListMultimap.create();
        List<AdvPermsDto> resultAdvPermsDto = Lists.newArrayList();
        //对menudto List进行迭代
        advMenuDtos.forEach(advMenuDto ->{
            //第一层是一个角色对应所有菜单树
            advMenuDto.forEach(roleMenuTree ->{
                //现在的循环的是菜单树组对应的一个个的菜单树,先判断菜单数组是否有子菜单(第一层)
                if(!CollectionUtils.isEmpty(roleMenuTree.getChildren())){
                    //当前循环对应的是第一层的部分
                    List<AdvMenuDto> firstMenuDto = roleMenuTree.getChildren();
                    //判断firstMenuDto是否为空，为空未进行存储.
                    firstMenuDto.forEach(viewMenuDto ->{
                        boolean flag = saveMap.containsKey(viewMenuDto.getMenuName());//判断map中是否包含menuName
                        AdvPermsDto saveAdvPermsDto;
                        if(flag){//如果包含了，不在继续创建
                            saveAdvPermsDto = saveMap.get(viewMenuDto.getMenuName());
                        }else{
                            saveAdvPermsDto = new AdvPermsDto();
                            //当前存储的是管理层菜单
                            saveAdvPermsDto.setTitle(viewMenuDto.getMenuName());
                            //应前端要求，添加菜单位置
                            CommonUtils.permMap.keySet().forEach(key ->{
                                if(viewMenuDto.getMenuName().equals(key)){
                                    saveAdvPermsDto.setIndex(CommonUtils.permMap.get(key).get(0));
                                }
                            });
                            saveAdvPermsDto.setChildren(Lists.newArrayList());
                            saveMap.put(saveAdvPermsDto.getTitle(),saveAdvPermsDto);
                        }
                        List<AdvMenuDto> functionMenu = viewMenuDto.getChildren();
                        List<AdvPermsDto> advPermsDtoList = saveAdvPermsDto.getChildren();

                        if(!CollectionUtils.isEmpty(functionMenu)){
                            functionMenu.forEach(menu ->{//当前是功能菜单
                                //判断当前menu是否存在map中
                                if(!multimap.containsKey(menu.getMenuName())){//如果不包含menuName，进行数据添加
                                    AdvPermsDto childAdvPermDto = new AdvPermsDto();
                                    childAdvPermDto.setTitle(menu.getMenuName());
                                    CommonUtils.permMap.keySet().forEach(key ->{
                                        if(childAdvPermDto.getTitle().equals(key)){
                                            childAdvPermDto.setPath(CommonUtils.permMap.get(key).get(1));
                                        }
                                    });
                                    advPermsDtoList.add(childAdvPermDto);
                                    //添加到mutimap中
                                    multimap.put(viewMenuDto.getMenuName(),childAdvPermDto.getTitle());
                                }
                            });
                        }
                        saveAdvPermsDto.setChildren(advPermsDtoList);
                        if(resultAdvPermsDto.contains(saveAdvPermsDto)){
                            int index = resultAdvPermsDto.indexOf(saveAdvPermsDto);
                            resultAdvPermsDto.set(index,saveAdvPermsDto);
                        }else{
                            resultAdvPermsDto.add(saveAdvPermsDto);
                        }
                    });
                }
            });
        });
        return resultAdvPermsDto;
    }

}
