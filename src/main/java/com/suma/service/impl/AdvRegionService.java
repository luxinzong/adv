package com.suma.service.impl;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.suma.constants.ExceptionConstants;
import com.suma.dao.AdvRegionMapper;
import com.suma.dto.AdvRegionDto;
import com.suma.exception.RegionException;
import com.suma.pojo.AdvRegion;
import com.suma.service.iAdvRegionService;
import com.suma.utils.AncestorUtil;
import com.suma.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @Autor gaozhongbao
 * @Date 2018/10/26 0026
 * @Description
 **/
@Service
public class AdvRegionService implements iAdvRegionService {

    @Autowired
    private AdvRegionMapper advRegionMapper;
    @Autowired
    private TreeService treeService;


    @Override
    public int insertAdvRegion(AdvRegion advRegion) {
        //判断是否已经存在了区域名称
        String regionName = advRegion.getRegionName();
        int result = advRegionMapper.checkAdvRegionNameUnique(regionName);
        if(result > 0){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_NAME_EXIST);
        }
        //设置orderNum，默认取当前数据库中最大值+1
        Integer orderNum = advRegion.getOrderNum();
        if(orderNum == null){
            Integer parentId = advRegion.getParentId();
            if(parentId == null){
                parentId = 0;
            }
            int currentMaxOrderNum = advRegionMapper.getMaxAdvRegionOrderNum(parentId);
            int newOrderNum = currentMaxOrderNum + 1;
            advRegion.setOrderNum(newOrderNum);
        }
        //查询父类区域
        AdvRegion parentAdvRegion = advRegionMapper.selectByPrimaryKey(advRegion.getParentId());
        if(parentAdvRegion != null){
            //添加祖先区域id
            advRegion.setAncestors(parentAdvRegion.getAncestors() + "," + advRegion.getParentId());
        }else{//当前没有父亲区域
            advRegion.setAncestors(AncestorUtil.ROOT);
        }

        advRegion.setCreateBy(ShiroUtils.getUser().getUserName());
        return advRegionMapper.insertSelective(advRegion);

    }

    @Override
    public int deleteAdvRegion(Integer regionId) {
        AdvRegion advRegion = advRegionMapper.selectByPrimaryKey(regionId);
        if(advRegion == null){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_ID_NOT_EXIST);
        }
        //查看区域是否存在下级区域
        if(advRegionMapper.selectAdvCountByParentId(regionId) > 0){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_EXIST_NEXT_AREA);
        }

        return advRegionMapper.deleteByPrimaryKey(regionId);

    }

    @Override
    public int updateAdvRegion(AdvRegion advRegion) {
        //判断区域id是否存在
        int regionId = advRegion.getRegionId();
        AdvRegion selectAdvRegion = advRegionMapper.selectByPrimaryKey(regionId);
        if(selectAdvRegion == null){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_ID_NOT_EXIST);
        }

        //修改对应祖先数据
        AdvRegion parentRegion = advRegionMapper.selectByPrimaryKey(advRegion.getParentId());
        if(parentRegion == null){//如果祖先为空
            advRegion.setAncestors(AncestorUtil.ROOT);//则当前区域为祖先节点
        }else{
            advRegion.setAncestors(parentRegion.getAncestors() + AncestorUtil.SEPARTOR + advRegion.getParentId());
        }

        advRegion.setUpdateBy(ShiroUtils.getUser().getUserName());
        return advRegionMapper.updateByPrimaryKeySelective(advRegion);
    }

    @Override
    public List<AdvRegionDto> selectAdvRegionList(AdvRegion advRegion) {
        List<AdvRegion> regionList = advRegionMapper.selectAdvRegionList(advRegion);
        if(CollectionUtils.isEmpty(regionList)){//如果查询数据为空，直接返回null
            return null;
        }
        List<AdvRegionDto> advRegionDtoList = produceAdvRegionDto(regionList);
        return advRegionDtoList;
    }

    /**
     * 查询全部区域
     *
     * @return
     */
    @Override
    public List<AdvRegion> selectAdvRegionAll() {
        List<AdvRegion> regionList = advRegionMapper.selectAdvRegionAll();
        return regionList;
    }

    /**
     * 根据区域Id查询区域信息
     *
     * @param regionId
     * @return
     */
    @Override
    public AdvRegion selectAdvRegionById(Integer regionId) {
        AdvRegion advRegion = advRegionMapper.selectByPrimaryKey(regionId);
        if(advRegion == null){
            throw new RegionException(ExceptionConstants.REGION_EXCEPTION_REGION_ID_NOT_EXIST);
        }
        return advRegion;
    }

    /**
     * 查询部门树
     *
     * @return
     */
    @Override
    public List<AdvRegionDto> selectAdvRegionTree() {
        //先获取全部区域信息
        List<AdvRegion> regionList = advRegionMapper.selectAdvRegionAll();
        //进行拼装区域树
        return CollectionUtils.isEmpty(regionList)?null:
                    produceAdvRegionTree(regionList,AncestorUtil.ROOT,null);
    }

    /**
     * 生产advRegionDtoList
     *
     * @param advRegionList
     * @return
     */
    private List<AdvRegionDto> produceAdvRegionDto(List<AdvRegion> advRegionList){
        if(CollectionUtils.isEmpty(advRegionList)){//
            return null;
        }
        List<AdvRegionDto> advRegionDtoList = Lists.newArrayList();
        Map<Integer,String> parentNameMap = Maps.newConcurrentMap();
        advRegionList.forEach(advRegion -> {
            parentNameMap.put(advRegion.getRegionId(),advRegion.getRegionName());
        });

        advRegionList.forEach(advRegion -> {
            AdvRegionDto advRegionDto = AdvRegionDto.adapt(advRegion);
            //添加parentName
            if(advRegion.getParentId() == 0){//当前是父类id
                advRegionDto.setParentName("无");
            }else{
                //获取当前父类名称
                String parentName = parentNameMap.get(advRegion.getParentId());
                advRegionDto.setParentName(parentName);
            }
            advRegionDtoList.add(advRegionDto);
        });

        return advRegionDtoList;
    }

    /**
     * 拼装成区域树
     *
     * @param advRegionList
     * @param ancestor
     * @param status
     * @return
     */
    private List<AdvRegionDto> produceAdvRegionTree(List<AdvRegion> advRegionList,String ancestor,String status){
        List<AdvRegionDto> dtoList = produceAdvRegionDto(advRegionList);
        return advRegionListToTree(dtoList,ancestor,status);
    }

    /**
     * 将区域List转化为树形结构
     *
     * @param advRegionList
     * @param ancestor
     * @param status
     * @return
     */
    private List<AdvRegionDto> advRegionListToTree(List<AdvRegionDto> advRegionList, String ancestor, String status) {
        if(CollectionUtils.isEmpty(advRegionList)){
            return null;
        }

        Multimap<String,AdvRegionDto> ancestorMutiMap = ArrayListMultimap.create();
        //生成rootList
        List<AdvRegionDto> rootList = Lists.newArrayList();
        //遍历
//        advRegionList.forEach(advRegionDto -> { //
//            //根据ancestor,存储对应region对象
//            if(StringUtils.isBlank(status)){//如果有状态参数，则对状态进行判断
//                ancestorMutiMap.put(advRegionDto.getAncestors(),advRegionDto);
//                if(advRegionDto.getAncestors().equals(ancestor)){
//                    rootList.add(advRegionDto);
//                }
//            }else{
//                if(advRegionDto.getStatus().equals(status)){
//                    ancestorMutiMap.put(advRegionDto.getAncestors(),advRegionDto);
//                }
//                if(advRegionDto.getAncestors().equals(ancestor) && advRegionDto.getStatus().equals(status)){
//                    rootList.add(advRegionDto);
//                }
//            }
//        });
        treeService.addRootToRootList(advRegionList,rootList,ancestor,status,ancestorMutiMap);
        //对root进行排序
        Collections.sort(rootList,advRegionDtoComparator);
        //进行递归拼装
        transformAdvRegionTree(rootList,ancestor,ancestorMutiMap);
        return rootList;
    }

    /**
     * 递归组装区域树
     *
     * @param advRegionDtoList
     * @param ancestor
     * @param ancestorMutiMap
     */
    private void transformAdvRegionTree(List<AdvRegionDto> advRegionDtoList, String ancestor, Multimap<String,AdvRegionDto> ancestorMutiMap) {
        advRegionDtoList.forEach(advRegionDto -> {
            //获取下级ancestor
            String nextAncestor = AncestorUtil.calculateAncestor(ancestor,advRegionDto.getRegionId());
            List<AdvRegionDto> tempAdvRegionList = (List<AdvRegionDto>) ancestorMutiMap.get(nextAncestor);
            if(tempAdvRegionList != null){
                //排序
                Collections.sort(tempAdvRegionList,advRegionDtoComparator);
                //进行下一层处理
                advRegionDto.setChildren(tempAdvRegionList);
                transformAdvRegionTree(tempAdvRegionList,nextAncestor,ancestorMutiMap);
            }
        });
    }

    private Comparator<AdvRegionDto> advRegionDtoComparator = new Comparator<AdvRegionDto>() {
        @Override
        public int compare(AdvRegionDto o1, AdvRegionDto o2) {
            return o1.getOrderNum()  - o2.getOrderNum();
        }
    };


    /**
     * 获取部门总数
     *
     * @return
     */
    @Override
    public int getAdvRegionCount() {
        return advRegionMapper.getAdvRegionCount();
    }
}
