package com.suma.controller;

import com.google.common.collect.Maps;
import com.suma.constants.AdvContants;
import com.suma.constants.ExceptionConstants;
import com.suma.exception.AdvRequestException;
import com.suma.pojo.*;
import com.suma.service.AdvInfoService;
import com.suma.service.InfoRegionService;
import com.suma.service.InfoVersionService;
import com.suma.utils.Result;
import com.suma.vo.AdvGathererVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author: luxinzong
 * @date: 2018/11/21 0021
 * @description 广告导入器，收集器
 */
@RestController
@RequestMapping("gatherer")
public class AdvGathererController extends BaseController {
    @Autowired
    private AdvInfoService advInfoService;
    @Autowired
    private InfoRegionService infoRegionService;
    @Autowired
    private InfoVersionService infoVersionService;

    /**
     * 获得广告收集器
     * @param advRequestVO
     * @return
     */
    @RequestMapping("getAdvGatherer")
    public AdvGathererVO getAdvGatherer(@RequestBody AdvRequestVO advRequestVO) {
        //判断参数
        if (StringUtils.isAnyBlank(advRequestVO.getSessionId(), advRequestVO.getClientId())) {
            throw new AdvRequestException(ExceptionConstants.ADV_REQUEST_MISSING_PARAMETERS, advRequestVO.getSessionId());
        }

        AdvGathererVO advGathererVO = new AdvGathererVO();
        advGathererVO.setSessionId(advRequestVO.getSessionId());

        //首先获取某一区域的所有发布状态的广告信息
        List<Long> list = infoRegionService.selectAdvByRegion(Integer.valueOf(advRequestVO.getRegionCode()));
        List<AdvInfo> advInfoList = advInfoService.getAdvSByIds(list);
        //advInfoList = advInfoList.stream().filter(p -> p.getStatus().equals(AdvContants.STATUS_PUTTING)).collect(Collectors.toList());
        //创建导入器
        Map<String,Map<AdvItem, MultipartFile>> map = new MultiValueMap();
        //导入广告资源
        Map<AdvItem, MultipartFile> multipartFileMap = new HashMap<>();
        getAdvInfos(advInfoList,multipartFileMap);
        //按区域区分不同导入器
        map.put(advRequestVO.getRegionCode(), multipartFileMap);
        advGathererVO.setMap(map);
        advGathererVO.setRegion(Integer.valueOf(advRequestVO.getRegionCode()));
        advGathererVO.setStatus(0);
        advGathererVO.setLastStatus(-1);
        return advGathererVO;
    }

    /**
     * 暂不支持，频道相关广告
     * @param advInfoList
     * @param multipartFileMap
     * @return
     */
    private Map<AdvItem, MultipartFile> getAdvInfos(List<AdvInfo> advInfoList,Map<AdvItem, MultipartFile> multipartFileMap) {
        //把开机LOGO广告
        addBootMaterial(getDiffrentAdv(advInfoList,AdvContants.START_LOGO_ADV_SUBTYPE_ID),multipartFileMap);
        //开机广告
        addBootMaterial(getDiffrentAdv(advInfoList, AdvContants.START_MACHINE_ADV_SUBTYPE_ID), multipartFileMap);
        //植入广告-片头
        addOtherMaterial(getDiffrentAdv(advInfoList, AdvContants.BEFORE_MOVIE_ADV_SUBTYPE_ID), multipartFileMap);
        //植入广告-暂停
        addOtherMaterial(getDiffrentAdv(advInfoList, AdvContants.SUSPAND_MOVIE_ADV_SUBTYPE_ID), multipartFileMap);
        //音量条广告
        addOtherMaterial(getDiffrentAdv(advInfoList, AdvContants.VOLUM_ADV_SUBTYPE_ID), multipartFileMap);
        //主菜单广告
        addOtherMaterial(getDiffrentAdv(advInfoList, AdvContants.MAIN_MENU_ADV_SUBTYPE_ID), multipartFileMap);
        //节目列表广告
        addOtherMaterial(getDiffrentAdv(advInfoList, AdvContants.LIST_ADV_SUBTYPE_ID), multipartFileMap);
        /*//切台广告
        getDiffrentAdv(advInfoList,AdvContants.CUT_CHANNEL_ADV_SUBTYPE_ID);*/
        /*//弹出广告
        getDiffrentAdv(advInfoList, AdvContants.POP_ADV_SUBTYPE_ID);*/

        return multipartFileMap;
    }

    private List<AdvInfo> getDiffrentAdv(List<AdvInfo> advInfoList,Long advTypeId) {
       return advInfoList.stream().filter(p -> p.getAdvTypeId().equals(advTypeId)).collect(Collectors.toList());
    }

    /**
     * 导入资源
     * @param list
     * @param multipartFileMap
     */
    public void addBootMaterial(List<AdvInfo> list, Map<AdvItem, MultipartFile> multipartFileMap) {
        if (!CollectionUtils.isEmpty(list)) {
            //开机广告
            AdvInfo advInfo = infoVersionService.getUpToDateAdv(list);
            advInfoService.setAdvMapByOne(multipartFileMap, advInfo);
        }
    }

    public void addOtherMaterial(List<AdvInfo> list, Map<AdvItem, MultipartFile> multipartFileMap) {
        if (!CollectionUtils.isEmpty(list)) {
            //音量条、主菜单、节目列表、植入广告
            if (!CollectionUtils.isEmpty(list)) {
                AdvInfo advInfo1 = list.get(0);
                advInfoService.setAdvMapByOne(multipartFileMap,advInfo1);
            }
        }
    }
}
